package com.itheima.bos.dao;

import com.itheima.bos.domain.base.Area;

public interface AreaDao extends BaseDao<Area> {

	public Area findByProvinceAndCityAndDistrict(String province, String city, String district);
	
	
	
}
