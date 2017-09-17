package com.itheima.bos.web.action;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.take_delivery.PageBean;
import com.itheima.bos.domain.take_delivery.Promotion;
import com.itheima.bos.utils.Constant;


@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace("/promotion")
public class PromotionAction extends BaseAction<Object> {

	@Override
	public Specification<Object> getSpecification() {
		return null;
	}
	//获取当前页和页面大小
	private Integer pageIndex;
	private Integer pageSize;
	
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex+1;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	//重写分页方法
	@Override
	@Action("findByPage")
	public String findByPage() throws Exception {		
		//注意pagination的默认当前页是从0开始的 而spring是从1开始的 所以以下要加1
		PageBean<Promotion> pageBean = WebClient
		.create(Constant.bos_back_url+"/services/promotionService/queryByPagePromotion?page="+pageIndex+"&pageSize="+pageSize)
		.accept(MediaType.APPLICATION_JSON)
		.get(PageBean.class);
		
		//给每个宣传任务的图片地址前面加上http://localhost:9080
		List<Promotion> list = pageBean.getList();
		for (Promotion promotion : list) {
			promotion.setTitleImg(Constant.bos_back_host+promotion.getTitleImg());
		}
		
		result.put("list", pageBean.getList());
		result.put("total", pageBean.getTotal());
		
		return writerJson(pageBean);
	}

}

	
	
	
	
