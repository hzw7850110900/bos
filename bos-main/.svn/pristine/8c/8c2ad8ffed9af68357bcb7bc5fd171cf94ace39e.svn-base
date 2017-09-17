package com.itheima.bos.service.take_delivery.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.take_delivery.WayBillDao;
import com.itheima.bos.domain.take_delivery.WayBill;
import com.itheima.bos.service.imp.BaseServiceImp;
import com.itheima.bos.service.take_delivery.WayBillService;



@Service("wayBillService")
@Transactional
public class WayBillServiceImp extends BaseServiceImp<WayBill> implements WayBillService {
	
	WayBillDao wayBillDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setWayBillDao(WayBillDao wayBillDao) {
		this.wayBillDao = wayBillDao;
		super.setBaseDao(wayBillDao);
	}
	@Override
	public WayBill findBywayBillNum(String wayBillNum) {
		return wayBillDao.findBywayBillNum(wayBillNum);
	}
	
	//批量删除
	@Override
	public void cancel(String delId) {
		//判空
		if(delId!=null && !delId.equals("")) {
			String[] ids = delId.split(",");
			for (String id : ids) {
				wayBillDao.cancel(Long.parseLong(id));
			}			
		}
	}
		
	
	
	@Override
	public List<WayBill> QueryByIsDelete() {
		return wayBillDao.QueryByIsDelete();
	}
	
	@Override
	public void updateByRestoreId(String restoreId) {
		
		if(restoreId!=null && !restoreId.equals("")) {
			String[] resIds = restoreId.split(",");
			for (String resId : resIds) {
				wayBillDao.updateByRestoreId(Long.parseLong(resId));
			}
		}
	}



}
