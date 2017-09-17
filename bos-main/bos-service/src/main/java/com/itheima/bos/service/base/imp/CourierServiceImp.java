package com.itheima.bos.service.base.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itheima.bos.dao.CourierDao;
import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.service.base.CourierService;
import com.itheima.bos.service.imp.BaseServiceImp;

@Service("CourierService")
public class CourierServiceImp extends BaseServiceImp<Courier> implements CourierService {
	
	CourierDao courierDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setCourierDao(CourierDao courierDao) {
		this.courierDao = courierDao;
		super.setBaseDao(courierDao);
	}
	@Override
	public void save(List<Courier> list) {
		// TODO Auto-generated method stub
		courierDao.save(list);
	}
	
	

	
	
}
