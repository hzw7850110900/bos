package com.itheima.bos.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.itheima.bos.utils.Constant;
import com.itheima.bos.utils.MailUtils;
import com.itheima.bos.utils.SmsUtils;
import com.itheima.crm.domain.Customer;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/customer")
public class CustomerAction extends BaseAction<Customer> {

	@Override
	public Specification<Customer> getSpecification() {
		return null;
	}

	// 验证码发送
	@Action(value = "send")
	public String sendSms() {		
		try {
			// 获取实体对象
			Customer customer = this.getModel();
			// 手机号
			String telephone = customer.getTelephone();
			System.out.println(telephone);
			// 随机生成四位验证码
			 String code = RandomStringUtils.randomNumeric(4);
			// 将验证码存储到session当中
			ServletActionContext.getRequest().getSession().setAttribute("code", code);
			System.out.println(code);
			//调用工具类方法 发送短信
			SmsUtils.sendMsg("微微物流", "{\"code\":\"" + code + "\"}", telephone, "SMS_70100113");
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		return writerJson(result);

	}

	// 验证手机号是否已经被注册
	@Action("checkPhoneIsExist")
	public String checkPhone() {
		// 手机号
		String telephone = getModel().getTelephone();
		// 使用WebService技术调用crm系统验证手机号是否已经被注册
		List<Customer> list = (List<Customer>) WebClient.create(
				Constant.crm_url+"/services/customerService/checkPhoneIsExist?telephone=" + telephone)
				.accept(MediaType.APPLICATION_JSON).getCollection(Customer.class);

		// 如果list长度为0 则不存在 返回true
		if (list.size() != 0) {
			return writerJson("该手机号已被注册!");
		} else {
			return NONE;
		}

	}

	// 接收客户注册时输入的验证码
	private String validCode;

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}
	
	//注入一个spring data整合redis的对象
	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Action("register")
	public String register() throws Exception {
		Customer customer = getModel();
		// 默认情况下手机号就是用户名
		if (StringUtils.isBlank(customer.getUsername())) {
			customer.setUsername(customer.getTelephone());
		}
		// 首先判断验证码是否是否正确
		// 1.先获取session中存储的验证码
		String code = (String) ServletActionContext.getRequest().getSession().getAttribute("code");
		System.out.println("code="+code);
		System.out.println("validCode="+code);
		if (code == null || !code.equals(validCode)) {
			result.put("success", false);
			result.put("msg", "验证码错误");
		} else {
			try {
				// 如果验证码匹配成功 则调用保存方法
				// 使用WebService技术调用customer的保存方法
				WebClient.create(Constant.crm_url+"/services/customerService/save")
						.type(MediaType.APPLICATION_JSON).post(customer);
				// 注册成功
				result.put("success", true);
				
				//注册成功后发送激活邮件到邮箱
				//生成一个随机激活码
				String activeCode=UUID.randomUUID().toString();
				System.out.println("激活码=="+activeCode);
				//主题
				String subject="微微物流系统注册激活邮件";
				//接收人
				String recMail="785011900@qq.com";
				//文件内容
				String content="尊敬的"+customer.getTelephone()+
						"用户:<br/>      您好!欢迎您注册微微物流系统,"
						+ "请在48小时内点击以下连接进行账号激活才可登陆<br/>"
						+"<a href='"+MailUtils.active_url+"?telephone="
						+customer.getTelephone()+"&activeCode="+activeCode+"'>点击激活</a>";
				System.out.println(activeCode);
				//调用工具类方法 发送邮件
				MailUtils.sendMail(content, subject, recMail);
				
				//将激活码存到redis中管理。并设置时间24小时
				redisTemplate.opsForValue().set(customer.getTelephone(), activeCode,24,TimeUnit.HOURS);			
			
				
				System.out.println("取出来的激活码=="+redisTemplate.opsForValue().get(customer.getTelephone()));
				
			} catch (Exception e) {
				result.put("success", false);
				result.put("msg", e.getMessage());
				e.printStackTrace();
			}
		}
		return writerJson(result);
	}
	
	private String activeCode;	
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	
	
	@Action("activeCustomer")
	public String activeCustomer() throws IOException{
		//获得手机号
		String telephone=getModel().getTelephone();
		//获取响应对象
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		//通过手机号取出redis存储的激活码
		String redisCode=redisTemplate.opsForValue().get(telephone);
		//判断当前激活码是否为空 或与redis当中存储的是否匹配
		if(activeCode==null || !activeCode.equals(redisCode)){
			response.getWriter().write("激活失败,激活码无效!");
			return NONE;
		}
		
		//验证码匹配成功 则 调通过WebService技术调用crm系统修改客户的激活状态
		WebClient.
		create(Constant.crm_url+"/services/customerService/activeCustomer?telephone="+telephone)
		.put(null);	
		
		//激活成功后输出信息
		response.getWriter().write("恭喜你,激活成功,快去登录吧!<a href='http://localhost:9082/bos_fore/login.html'>去登录</a>");
		//激活成功后则删除redis当中的此激活码
		redisTemplate.delete(telephone);
		return NONE;
	}
	@Action("login")
	public String login(){
		//获取手机号和密码
		Customer customer=getModel();
		//手机号
		String telephone=customer.getTelephone();
		//利用webService调用crm技术
		Customer loginCustomer = WebClient.
		create(Constant.crm_url+"/services/customerService/login?telephone="+telephone+"&password="+customer.getPassword())
		.accept(MediaType.APPLICATION_JSON)
		.get(Customer.class);
		
		//首先判断LoginCustomer是否为空
		if(loginCustomer==null){
			//用户名密码错误
			result.put("msg", "用户名或密码错误");	
			result.put("success", false);
		}else{
			//判断激活码是否激活
			String type=loginCustomer.getType();
			if(type==null || ! type.equals("1")){
				result.put("msg", "您的账户尚未激活,请去邮箱激活再来登录");	
				result.put("success", false);
			}else{
				result.put("success", true);
				//登录成功则把用户信息存储到session当中
				ServletActionContext.getRequest().getSession().setAttribute("loginCustomer", loginCustomer);
			}
		}		
		return writerJson(result);
		
	}
}
