package com.itheima.bos.realm;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.itheima.bos.dao.system.UserDao;
import com.itheima.bos.domain.system.Resources;
import com.itheima.bos.domain.system.User;

public class BosRealm extends AuthorizingRealm {
	
	//诸如userdao
	@Resource
	UserDao userDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//开始授权
		System.out.println("开始授权");
		//资源授权
		SimpleAuthorizationInfo  info=new SimpleAuthorizationInfo ();
		//获取当前用户
		Subject subject = SecurityUtils.getSubject();
		//当前登录用户
		User loginUser = (User) subject.getPrincipal();
		//通过当前用户去查询所有资源 取出资源码grantKey
		List<Resources> resList = userDao.findMyPerms(loginUser.getId());
		//遍历
		for (Resources resources : resList) {
			
			if(resources.getGrantKey()!=null && !resources.getGrantKey().equals("")){
				//添加授权
				info.addStringPermission(resources.getGrantKey());
			}
			
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("开始认证");
		//向下强转
		UsernamePasswordToken userToken=(UsernamePasswordToken)token;
		
		//判断用户名是否存在
		User loginUser = userDao.findByUsername(userToken.getUsername());
		System.out.println("当前登录用户是"+loginUser);
		if(loginUser==null){
			//用户名不存在
			return null;
		}
		//判断密码
		return new SimpleAuthenticationInfo(loginUser, loginUser.getPassword(), "");
	}

}
