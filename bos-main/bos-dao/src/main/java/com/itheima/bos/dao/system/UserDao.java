package com.itheima.bos.dao.system;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.dao.BaseDao;
import com.itheima.bos.domain.system.Resources;
import com.itheima.bos.domain.system.User;

public interface UserDao extends BaseDao<User> {

	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);

	//查询当前用户的所有的资源
	@Query("select res from User u inner join u.roles r inner join r.resourceses res where u.id=? and res.resourceType='0' order by res.seq asc")
	List<Resources> findMyMenus(Long id);
	
	@Query("select res from User u inner join u.roles r inner join r.resourceses res where u.id=?")
	List<Resources> findMyPerms(Long id);

	
	
	
}
