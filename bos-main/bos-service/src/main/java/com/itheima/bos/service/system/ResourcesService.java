package com.itheima.bos.service.system;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.bos.domain.system.Resources;
import com.itheima.bos.service.BaseService;

public interface ResourcesService extends BaseService<Resources> {

	Page<Resources> findByPage(Specification<Resources> spec, Pageable pageable, Long roleId);


	

	
	
}
