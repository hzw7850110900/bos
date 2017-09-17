package com.itheima.bos.service.take_delivery;




import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.bos.domain.take_delivery.WayBill;
import com.itheima.bos.service.BaseService;

public interface WayBillService extends BaseService<WayBill> {

	public WayBill findBywayBillNum(String wayBillNum);

	public void cancel(String delId);
					
	public List<WayBill> QueryByIsDelete();

	public void updateByRestoreId(String restoreId);

	//public WayBill findByWayBillNum(String wayBillNum);

	
	
}
