package com.itheima.bos.service.take_delivery.imp;



import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService.Work;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.AreaDao;
import com.itheima.bos.dao.CourierDao;
import com.itheima.bos.dao.FixedAreaDao;
import com.itheima.bos.dao.take_delivery.OrderDao;
import com.itheima.bos.dao.take_delivery.WorkBillDao;
import com.itheima.bos.domain.base.Area;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.domain.take_delivery.Order;
import com.itheima.bos.domain.take_delivery.WorkBill;
import com.itheima.bos.service.imp.BaseServiceImp;
import com.itheima.bos.service.take_delivery.OrderService;
import com.itheima.bos.utils.Constant;
import com.itheima.bos.utils.MailUtils;
import com.itheima.bos.utils.SmsUtils;
import com.itheima.crm.domain.Customer;
import com.mchange.v2.c3p0.ConnectionCustomizer;

@Service("orderService")
@Transactional
public class OrderServiceImp extends BaseServiceImp<Order> implements OrderService {
	
	OrderDao orderDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
		super.setBaseDao(orderDao);
	}
	@Resource
	AreaDao areaDao;
	
	@Resource
	FixedAreaDao fixedAreaDao;
	@Resource
	CourierDao courierDao;
	
	@Resource
	WorkBillDao workBillDao;
	/*
	 * 保存订单
	 * */
	@Override
	public void saveOrder(Order order) {
		System.out.println("开始=======================");
		//生成订单号
		String orderNum = UUID.randomUUID().toString();
		order.setOrderNum(orderNum);
		
		//通过省市区查找收件人和寄件人的区域，关联订单		
		//查询寄件人的区域信息
		Area sendArea = order.getSendArea();
		Area persistSendArea=areaDao.findByProvinceAndCityAndDistrict(sendArea.getProvince(),sendArea.getCity(),sendArea.getDistrict());
		//查询收件人区域的
		Area recArea=order.getRecArea();
		Area persistRecArea=areaDao.findByProvinceAndCityAndDistrict(recArea.getProvince(), recArea.getCity(), recArea.getDistrict());
		//封装到对象当中
		order.setSendArea(persistSendArea);
		order.setRecArea(persistRecArea);
		
		//设置下单状态
		order.setStatus("1");//已下单
		//订单时间
		order.setOrderTime(new Date());
		
		//进行自动分单，如果分单失败，就人工分单
		/*
		 * 自动分单思路: 
		 * 		1.通过客户详细地址完全匹配进行自动分单
		 * 			通过客户填写的详细地址去与客户表中的地址匹配，如果完全匹配成功,则通过客户找定区-->再通过定区找快递员
		 *      2.通过客户区域+详细地址匹配进行自动分单
		 *      	通过填写的寄件人的区域查找对应的区域，再通过详细地址与该区域下所有的分区的关键字去匹配,-->匹配成功通过分区找定区-->定区找快递员
		 * */
		 //查找当前下单对应的客户
		Customer customer = WebClient
		.create(Constant.crm_url+"/services/customerService/findByAddress/"+order.getSendAddress())
		.accept(MediaType.APPLICATION_JSON)
		.get(Customer.class);
		 
		if(customer!=null){
			//如果查询到的客户不为空,则通过客户查询所属定区
			Long fixedAreaId = customer.getFixedAreaId();
			//通过定区查快递员
			if(fixedAreaId!=null){
				FixedArea fixedArea = fixedAreaDao.findOne(fixedAreaId);
				//通过定区查快递员
				if(fixedArea!=null){
					Courier courier = fixedArea.getCourier();
					if(courier!=null){
						//设置订单分配状态为自动分单
						order.setOrderType("1");
						//保存订单
						orderDao.save(order);
						//创建取件工单
						createWorkBill(order,courier);
						//修改订单的分配状态为已分单
						return ;
					}
				}  
			}
		}
		
		//通过省市区找区域信息，再通过区域找分区
		if(persistSendArea!=null){
			Set<SubArea> subAreas = persistSendArea.getSubAreas();
			//通过客户的地址匹配分区当中的主关键字和辅助关键字
			for (SubArea subArea : subAreas) {				
				if(order.getSendAddress().contains(subArea.getKeyWords()) || order.getSendAddress().contains(subArea.getAssistKeyWords())){
					  //匹配成功则找到分区,通过分区找定区
					FixedArea fixedArea = subArea.getFixedArea();
					if(fixedArea!=null){
						//通过定区找快递员
						Courier courier = fixedArea.getCourier();
						if(courier!=null){
							//设置订单分配状态为自动分单
							order.setOrderType("1");
							//保存订单
							orderDao.save(order);
							//创建取件工单
							createWorkBill(order,courier);
							//修改订单的分配状态为已分单
							return ;
						}
					}
				}
			}
		}		
		
		//如果自动分单失败 则是人工分单
		order.setOrderType("2");
		System.out.println("需要人工分单");
		//保存订单
		orderDao.save(order);
	}
	//创建工单
	
	public void  createWorkBill(Order order,Courier courier){
		//创建一个工单对象
		WorkBill workBill=new WorkBill();
		//设置所属订单
		workBill.setOrder(order);
		//设置工单状态
		workBill.setPickstate("1"); //未通知
		//设置工单创建时间
		workBill.setBuildtime(new Date());
		//设置取件快递员
		workBill.setCourier(courier);
		//设置工单备注
		workBill.setRemark("由"+courier.getTelephone()+"取件");
		
		//保存工单
		workBillDao.save(workBill);
		
		//发送内容
		//保存成功后发送短信给快递员通知取件
		SmsUtils.sendMsg("微微物流",
				"{\"name\":\""+courier.getName()+"\",\"address\":\""+order.getSendAddress()+"\",\"telephone\":\""+order.getSendMobile()+"\"}",
				courier.getTelephone(), "SMS_80790033");
		
		//发送成功将工单状态修改成已通知
		workBill.setPickstate("2");//已通知
	}
	
	
	@Override
	public Order findByorderNum(String orderNum) {
		return orderDao.findByorderNum(orderNum);
	}

	@Override
	public List<Courier> findCourierByArea(Long id) {
		List<Courier>list=orderDao.findCourierByArea(id);
		//定义set集合存储快递员的id
		Set<Long> idSet=new HashSet<>();
		for (Courier courier : list) {
			idSet.add(courier.getId());
		}
		//清空list
		idSet.clear();
		//再通过id查询快递员
		for (Long courierId : idSet) {
			list.add(courierDao.findOne(courierId));
		}
		return list ;
	}
	@Override
	public void houseOrder(Order perOrder, Courier courier) {
		 
		//创建工单
		createWorkBill(perOrder, courier);
		
		//修改订单的分配状态为已分单
		perOrder.setOrderType("3");
		
	}
	
	
	//取消指定订单号的订单
	@Override
	public void cancleOrder(Long orderId) {
		//从数据库查询到持久态对象
		Order order = orderDao.findOne(orderId);
		//修改持久态对象的订单状态
		order.setStatus("7");;
		
		//调用工具类,发送邮件
		try {
			//获取订单的客户id
			Long customerId = order.getCustomerId();
			//远程调用crm系统查询客户
			Customer customer = (Customer)WebClient
				.create(Constant.crm_url+"/services/customerService/findCustomerById?customerId="+customerId)
				.accept(MediaType.APPLICATION_JSON)
				.get(Customer.class);
			//获取客户的邮箱
			String email = customer.getEmail();
			//发送邮件
			MailUtils.sendMail("尊敬的用户:您的订单号为"+order.getOrderNum()+"的订单已经取消下单!", "取消订单", email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
