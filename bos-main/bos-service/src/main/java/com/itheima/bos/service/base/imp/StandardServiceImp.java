package com.itheima.bos.service.base.imp;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itheima.bos.dao.StandardDao;
import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.StandardService;
import com.itheima.bos.service.imp.BaseServiceImp;

@Service("StandardService")
public class StandardServiceImp extends BaseServiceImp<Standard> implements StandardService {
	
	StandardDao standardDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setStandardDao(StandardDao standardDao) {
		this.standardDao = standardDao;
		super.setBaseDao(standardDao);
	}
	@Override
	public Standard findByNames(String stanName) {
		// TODO Auto-generated method stub
		return standardDao.findByNames(stanName);
	}
	
	

	
	
}
