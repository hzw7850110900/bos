package com.itheima.bos.web.take_delivery.action;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.take_delivery.Order;
import com.itheima.bos.service.base.CourierService;
import com.itheima.bos.service.take_delivery.OrderService;
import com.itheima.bos.web.action.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/order")
@ParentPackage("struts-default")
public class OrderAction extends BaseAction<Order> {

	OrderService orderService;

	@Resource
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
		super.setBaseService(orderService);
	}

	@Override
	public Specification<Order> getSpecification(){
		//获取传过来的模型页面对象
		final Order model = getModel();
		// 条件对象
		Specification<Order> spec = new Specification<Order>() {
			@Override
			public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//定义集合存放条件
				List<Predicate> preList = new ArrayList<Predicate>();
				//条件拼接  订单号
				if(model.getOrderNum()!=null&&!"".equals(model.getOrderNum())){
					preList.add(cb.equal(root.get("orderNum").as(String.class), model.getOrderNum()));
				}
				//寄件人姓名sendName
				if(model.getSendName()!=null&&!"".equals(model.getSendName())){
					preList.add(cb.equal(root.get("sendName").as(String.class), model.getSendName()));
				}
				//收件人姓名recName
				if(model.getRecName()!=null&&!"".equals(model.getRecName())){
					preList.add(cb.equal(root.get("recName").as(String.class), model.getRecName()));
				}
				//收件人手机recMobile
				if(model.getRecMobile()!=null&&!"".equals(model.getRecMobile())){
					preList.add(cb.equal(root.get("recMobile").as(String.class), model.getRecMobile()));
				}
				//快递产品类型sendProNum
				if(model.getSendProNum()!=null&&!"".equals(model.getSendProNum())){
					preList.add(cb.equal(root.get("sendProNum").as(String.class), model.getSendProNum()));
				}				
				//下单时间orderTime
				if(model.getOrderTime()!=null&&!"".equals(model.getOrderTime())){
					//获取用户填写时间对象
					Date date = model.getOrderTime();
					//向后推移一天
					Date tomorrow = DateUtils.addDays(date, 1);

					preList.add(cb.between(root.get("orderTime").as(Date.class),date,tomorrow));
				}
				//订单状态status
				if(model.getStatus()!=null&&!"".equals(model.getStatus())){														
					preList.add(cb.equal(root.get("status").as(String.class), model.getStatus()));
				}
				//订单类型
				if(model.getOrderType()!=null&&!"".equals(model.getOrderType())){
					preList.add(cb.equal(root.get("orderType").as(String.class), model.getOrderType()));
				}

				Predicate[] preArray = new Predicate[preList.size()];
				return cb.and(preList.toArray(preArray));
			}

		};
		return spec;

	}
	
	//根据订单号查询
	@Action("findByorderNum")
	public String findByorderNum(){
		//通过订单号查询数据
		Order order=orderService.findByorderNum(getModel().getOrderNum());
		return writerJson(order);
	}
	
	//取消订单操作
	@Action("cancelOrder")
	public String cancelOrder(){
		try {
			//获取页面传过来的参数
			Long orderId = getModel().getId();
			//调用业务层的方法
			orderService.cancleOrder(orderId);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		return writerJson(result);
	}

	
	//找订单区域的所有快递员
	@Action("findCourierByArea")
	public String findCourierByArea(){
		
		//查询当前订单区域的所有快递员
		Long  id=getModel().getId();
		
		List<Courier> list=orderService.findCourierByArea(id);
		
		
		return writerJson(list);
	}
	//注入CourierService
	@Resource
	CourierService courierService;
	//接收快递员id
	private Long courierId;		
	
	public void setCourierId(Long courierId) {
		this.courierId = courierId;
	}

	//人工分单
	@Action("houseOrder")
	public String houseOrder(){
		
		try {
			//获取订单对象
			Order order=getModel();
			//查询订单的持久化对象
			Order perOrder=orderService.get(order.getId());
			//根据快递员id查询
			Courier courier = courierService.get(courierId);
			if(perOrder!=null  && courier!=null){
				//绑定工单 发送短信 给快递员
				orderService.houseOrder(perOrder, courier);							
				
			}
			//成功
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}
		
		return writerJson(result);
		
	}

	
}
