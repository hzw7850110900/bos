package com.itheima.bos.service.base;



import java.util.List;

import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.service.BaseService;

public interface CourierService extends BaseService<Courier> {


	void save(List<Courier> list);
	
}
