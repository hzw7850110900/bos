package com.itheima.bos.service.system;

import java.util.List;

import com.itheima.bos.domain.system.Resources;
import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.BaseService;
import com.itheima.bos.utils.LoginFailException;

public interface UserService extends BaseService<User> {

	void bindRole(Long userId, String roleIds);

	User login   (User user)throws LoginFailException;

	List<Resources> findMyMenus(Long id);



	

	
	
}
