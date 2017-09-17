package com.itheima.bos.service.system;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.itheima.bos.domain.system.Role;
import com.itheima.bos.service.BaseService;

public interface RoleService extends BaseService<Role> {

	void bindResoureces(Long roleId, String resId);

	Page<Role> findByPage(Specification<Role> spec, Pageable pageable, Long roleId);


	

	
	
}
