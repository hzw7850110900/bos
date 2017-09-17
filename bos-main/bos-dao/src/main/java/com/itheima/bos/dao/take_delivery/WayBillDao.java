package com.itheima.bos.dao.take_delivery;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.dao.BaseDao;
import com.itheima.bos.domain.take_delivery.WayBill;
//工单
public interface WayBillDao extends BaseDao<WayBill> {

	public WayBill findBywayBillNum(String wayBillNum);

	@Query("update WayBill set isDelete='0' where id=?")
	@Modifying
	public void cancel(Long id);

	//查询所有可以还原的
	@Query("from WayBill where isDelete='0'")
	public List<WayBill> QueryByIsDelete();

	@Query("update WayBill set isDelete='1' where isDelete='0' and id=?")
	@Modifying
	public void updateByRestoreId(Long id);

	public WayBill findByWayBillNum(String wayBillNum);

		
	
}
