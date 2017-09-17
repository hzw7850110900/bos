package com.itheima.bos.service.system.imp;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itheima.bos.dao.system.RoleDao;
import com.itheima.bos.dao.system.UserDao;
import com.itheima.bos.domain.system.Resources;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.imp.BaseServiceImp;
import com.itheima.bos.service.system.UserService;
import com.itheima.bos.utils.LoginFailException;

@Service("userService")
public class UserServiceImp extends BaseServiceImp<User> implements UserService {
	
	UserDao userDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		super.setBaseDao(userDao);
	}
	@Resource
	RoleDao roleDao;
	
	@Override
	public void bindRole(Long userId, String roleIds) {
		//获取当前用户的持久态对象
		User user=userDao.findOne(userId);
		//绑定角色之前先清空之前绑定过的角色
		user.setRoles(null);
		
		//解析要绑定的角色的字符串
		String[] roleIdArr = roleIds.split(",");
		//定义一个set集合存储要绑定的角色对象
		Set<Role> roles=new HashSet<>();
		
		//循环遍历数组 根据id查询对象
		for (String roleId : roleIdArr) {
			Role role = roleDao.findOne(Long.parseLong(roleId));
			//存储进集合
			roles.add(role);
		}
		
		//绑定角色
		user.setRoles(roles);
		
	}

	@Override
	public User login(User user) throws LoginFailException {
		//通过用户名去数据库中查找
		User loginUser=userDao.findByUsername(user.getUsername());
		//判断用户名是否存在
		if(loginUser==null){
			throw new LoginFailException("用户名不存在");
		}
		//判断密码是否正确
		if(!loginUser.getPassword().equals(user.getPassword())){
			throw new LoginFailException("输入的密码错误");
		}
		
		//判断是否被禁用
		if(!loginUser.getStation().equals("1")){
			throw new LoginFailException("该用户已被禁用,请联系管理员");
		}
				
		return loginUser;
	}

	@Override
	public List<Resources> findMyMenus(Long id) {
		return userDao.findMyMenus(id);
	}

	
	
	
	
	

	
	
}
