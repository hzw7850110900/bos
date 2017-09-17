package com.itheima.bos.web.action;



import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Area;
import com.itheima.bos.domain.take_delivery.Order;
import com.itheima.bos.utils.Constant;
import com.itheima.crm.domain.Customer;


@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/order")
public class OrderAction extends BaseAction<Order> {

	@Override
	public Specification<Order> getSpecification() {
		return null;
	}
	
	//接收寄件人区域信息 --使用cityPicker省级联动传输过来的数据的格式是 省/市/区
	private String sendAreaInfo;
		
	public void setSendAreaInfo(String sendAreaInfo) {
		this.sendAreaInfo = sendAreaInfo;
	}
	//接收收件人的区域信息
	private String recAreaInfo;		

	public void setRecAreaInfo(String recAreaInfo) {
		this.recAreaInfo = recAreaInfo;
	}

	@Override
	@Action(value="save",results=@Result(name="login",location="/login.html"))
	public String save() throws Exception {
		try {
			Order order=getModel();
			//从会话域中取出当前客户关联订单
			Customer loginCustomer = (Customer) ServletActionContext.getRequest().getSession().getAttribute("loginCustomer");
			//订单关联客户
			if(loginCustomer!=null){
				order.setCustomerId(loginCustomer.getId());
			}else{
				return "login";
			}
			
			//切割寄件人封装到区域对象当中
			if(sendAreaInfo!=null){
				String[] sendAreaArray = sendAreaInfo.split("/");
				Area sendArea=new Area();
				sendArea.setProvince(sendAreaArray[0]);
				sendArea.setCity(sendAreaArray[1]);
				sendArea.setDistrict(sendAreaArray[2]);
				//封装到订单中
				order.setSendArea(sendArea);
			}
			
			if(recAreaInfo!=null){
				//解析收件人信息 封装到对象中
				String[] recAreaArray = recAreaInfo.split("/");
				Area recArea=new Area();
				recArea.setProvince(recAreaArray[0]);
				recArea.setCity(recAreaArray[1]);
				recArea.setDistrict(recAreaArray[2]);
				//封装到对象
				order.setRecArea(recArea);
			}
					
			System.out.println(order);
			//将订单发到后端系统
			WebClient
			.create(Constant.bos_back_url+"/services/orderService")
			.type(MediaType.APPLICATION_JSON)
			.post(order);
			//保存成功
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}		
		return writerJson(result);
	}
}
	
	
	
