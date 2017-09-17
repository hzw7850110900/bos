package com.itheima.bos.web.system.action;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.system.Resources;
import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.system.UserService;
import com.itheima.bos.utils.LoginFailException;
import com.itheima.bos.web.action.BaseAction;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@Namespace("/user")
@ParentPackage("struts-default")
public class UserAction extends BaseAction<User> {
	
	
	UserService userService;
		
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
		super.setBaseService(userService);
	}

	@Override
	public Specification<User> getSpecification() {
		final User user = getModel();
		// 条件的拼接
		// 条件对象
		return new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<>();
				
				
				
				Predicate[] arr = new Predicate[preList.size()];
				return cb.and(preList.toArray(arr));
			}
		};

	}
	
	//接收用户id
	private Long userId;
	//接收要绑定的角色id字符串
	private String roleIds;
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	@Action("bindRole")
	public String bindRole() throws Exception {		
		try {
			userService.bindRole(userId,roleIds);
			//绑定成功
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}
		return writerJson(result);
	}
	
	//接收验证码
	private String validCode;
		
	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	@Action("login")
	public String login(){
		User user = this.getModel();
		/*1.判断验证码是否正确--在页面中我们是通过vaildatacode.jsp去获取验证码的。
		在validatacode.jsp生成验证码的时候，已经将验证码放入session域对象中了*/
		//获取域对象中的验证码
		String code = (String) ActionContext.getContext().getSession().get("key");
		if(!validCode.equals(code)){
			result.put("success", false);
			result.put("msg", "验证码输入错误");
			return writerJson(result);
		}
		//使用shiro的MD5对密码进行处理
		//shiro加密
		/*
		 * 参数一 原始密码
		 * 参数二  salt  盐  相当于一个变量
		 * 参数三 相当于对盐计算几次
		 * */
		Md5Hash hash = new Md5Hash(user.getPassword(), user.getUsername(), 2);
		//设置
		user.setPassword(hash.toString());
		
		//2.使用shiro进行验证
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
			//开始验证--登录操作
			subject.login(token);			
			//验证成功则将对象保存到会话域
			result.put("success", true);
			//获取当前的登录对象
			User loginUser = (User) subject.getPrincipal();
			//存入域对象中
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", loginUser);
			
			//判断用户是否启用
			if(!loginUser.getStation().equals("1")){
				//状态码不等于说明被禁用
				result.put("success", false);
				result.put("msg", "用户被禁用");
			}
			//用户名不存在时捕获UnknownAccountException异常
		} catch (UnknownAccountException e) {	
			System.out.println("用户名不存在");
			result.put("success", false);
			result.put("msg", "用户名不存在");			
			//密码错误捕获AuthenticationException异常
		}catch (AuthenticationException e) {	
			System.out.println("密码错误");
			result.put("success", false);
			result.put("msg", "密码错误");
			e.printStackTrace();
		}catch (Exception e) {	
			result.put("success", false);
			result.put("msg", "系统异常");
		}
		
		
		return writerJson(result);
	}
	
	//获取当前用户的所有的资源
	@Action("findMyMenus")
	public String  findMyMenus(){
		//获取当前用户
		User loginUser=(User) ActionContext.getContext().getSession().get("loginUser");
		if(loginUser!=null){
			List<Resources> list=userService.findMyMenus(loginUser.getId());
			return writerJson(list);
		}
		
		return NONE;
	}
	
	//用户的注销
	@Action(value="logout",results={@Result(name="success",location="/login.jsp",type="redirect")})
	public String logout(){
		//要退出需要清除掉shiro当中的登录信息的缓存
		Subject subject = SecurityUtils.getSubject();
		//调用退出的方法shiro会自动去清除
		subject.logout();	
		return SUCCESS;
		
	}
	
	@Override
	@Action("save")
	public String save() throws Exception {
		//使用shiro的MD5对密码进行处理
		User user=getModel();
		//shiro加密
		/*
		 * 参数一 原始密码
		 * 参数二  salt  盐  相当于一个变量
		 * 参数三 相当于对盐计算几次
		 * */
		Md5Hash hash = new Md5Hash(user.getPassword(), user.getUsername(), 2);
		//设置
		user.setPassword(hash.toString());
		try {
			userService.save(user);
			// 如果保存成功
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			// 如果捕捉到异常失败
			result.put("success", false);
			result.put("message", e.getMessage());
		}
		
		return writerJson(result);
	}
}
