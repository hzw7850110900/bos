package com.itheima.bos.service.base.imp;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itheima.bos.dao.BusManagerDao;
import com.itheima.bos.domain.base.BusManager;
import com.itheima.bos.service.base.BusManagerService;
import com.itheima.bos.service.imp.BaseServiceImp;

@Service("BusManagerService")
public class BusManagerServiceImp extends BaseServiceImp<BusManager> implements BusManagerService {
	
	BusManagerDao busManagerDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setBusManagerDao(BusManagerDao busManagerDao) {
		this.busManagerDao = busManagerDao;
		super.setBaseDao(busManagerDao);
	}
	
	

	
	
}
