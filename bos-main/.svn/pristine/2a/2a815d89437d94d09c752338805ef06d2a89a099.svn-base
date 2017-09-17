package com.itheima.bos.service.take_delivery.imp;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itheima.bos.dao.take_delivery.WorkBillDao;
import com.itheima.bos.domain.take_delivery.WorkBill;
import com.itheima.bos.service.imp.BaseServiceImp;
import com.itheima.bos.service.take_delivery.WorkBillService;



@Service("workBillService")
@Transactional
public class WorkBillServiceImp extends BaseServiceImp<WorkBill> implements WorkBillService {
	
	WorkBillDao workBillDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setWorkBillDao(WorkBillDao workBillDao) {
		this.workBillDao = workBillDao;
		super.setBaseDao(workBillDao);
	}
	
	
}
