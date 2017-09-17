package com.itheima.bos.service.base.imp;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itheima.bos.dao.SubAreaDao;
import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.base.SubAreaService;
import com.itheima.bos.service.imp.BaseServiceImp;

@Service("subAreaService")
public class SubAreaServiceImp extends BaseServiceImp<SubArea> implements SubAreaService {
	
	SubAreaDao subAreaDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setSubAreaDao(SubAreaDao subAreaDao) {
		this.subAreaDao = subAreaDao;
		super.setBaseDao(subAreaDao);
	}
	
	

	
	
}
