package com.itheima.bos.service.base.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.itheima.bos.dao.FixedAreaDao;
import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.service.base.FixedAreaService;
import com.itheima.bos.service.imp.BaseServiceImp;

@Service("fixedFixedAreaService")
public class FixedAreaServiceImp extends BaseServiceImp<FixedArea> implements FixedAreaService {
	
	FixedAreaDao fixedAreaDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setFixedAreaDao(FixedAreaDao fixedAreaDao) {
		this.fixedAreaDao = fixedAreaDao;
		super.setBaseDao(fixedAreaDao);
	}
	@Override
	public void save(List<FixedArea> list) {
		
		fixedAreaDao.save(list);
		
	}
	
	
	

	
	
}
