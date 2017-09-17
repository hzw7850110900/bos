package com.itheima.bos.service.base.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.itheima.bos.dao.AreaDao;
import com.itheima.bos.domain.base.Area;
import com.itheima.bos.service.base.AreaService;
import com.itheima.bos.service.imp.BaseServiceImp;

@Service("AreaService")
public class AreaServiceImp extends BaseServiceImp<Area> implements AreaService {
	
	AreaDao areaDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
		super.setBaseDao(areaDao);
	}
	@Override
	public void save(List<Area> list) {
		
		areaDao.save(list);
		
	}
	
	
	

	
	
}
