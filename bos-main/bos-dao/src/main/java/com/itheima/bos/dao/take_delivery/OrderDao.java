package com.itheima.bos.dao.take_delivery;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.dao.BaseDao;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.take_delivery.Order;
//订单
public interface OrderDao extends BaseDao<Order> {

	Order findByorderNum(String orderNum);


	//根据订单发货区域内所有快递员
	@Query("select c from Order o inner join "
			+ "o.sendArea sea inner join "
			+ "sea.subAreas sub inner join "
			+ "sub.fixedArea fix inner join "
			+ "fix.courier c where o.id=? ")
	List<Courier> findCourierByArea(Long id);

		
	
}
