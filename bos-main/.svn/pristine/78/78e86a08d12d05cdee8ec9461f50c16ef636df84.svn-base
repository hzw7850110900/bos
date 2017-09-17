package com.itheima.bos.service.take_delivery;



import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.take_delivery.Order;
import com.itheima.bos.service.BaseService;

public interface OrderService extends BaseService<Order> {
	
	//保存订单
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveOrder(Order order);

	public Order findByorderNum(String orderNum);


	public List<Courier> findCourierByArea(Long id);
	
	public void cancleOrder(Long orderId);
	
	public void houseOrder(Order perOrder, Courier courier);
}
