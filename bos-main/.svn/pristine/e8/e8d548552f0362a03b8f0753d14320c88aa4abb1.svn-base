package com.itheima.bos.service.base.imp;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itheima.bos.dao.TakeDeliveryTimeDao;
import com.itheima.bos.domain.base.TakeDeliveryTime;
import com.itheima.bos.service.base.TakeDeliveryTimeService;
import com.itheima.bos.service.imp.BaseServiceImp;

@Service("TakeDeliveryTimeService")
public class TakeDeliveryTimeServiceImp extends BaseServiceImp<TakeDeliveryTime> implements TakeDeliveryTimeService {
	
	TakeDeliveryTimeDao takeDeliveryTimeDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setTakeDeliveryTimeDao(TakeDeliveryTimeDao takeDeliveryTimeDao) {
		this.takeDeliveryTimeDao = takeDeliveryTimeDao;
		super.setBaseDao(takeDeliveryTimeDao);
	}
	
	

	
	
}
