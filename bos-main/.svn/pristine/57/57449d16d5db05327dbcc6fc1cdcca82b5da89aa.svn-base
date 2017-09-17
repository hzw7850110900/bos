package com.itheima.bos.web.system.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.system.Resources;
import com.itheima.bos.service.system.ResourcesService;
import com.itheima.bos.web.action.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/resources")
@ParentPackage("struts-default")
public class ResourcesAction extends BaseAction<Resources> {

	ResourcesService resourcesService;

	@Resource
	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
		super.setBaseService(resourcesService);
	}

	@Override
	public Specification<Resources> getSpecification() {
		final Resources resources = getModel();
		// 条件的拼接
		// 条件对象
		return new Specification<Resources>() {
			@Override
			public Predicate toPredicate(Root<Resources> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<>();

				Predicate[] arr = new Predicate[preList.size()];
				return cb.and(preList.toArray(arr));
			}
		};

	}

	// 角色id
	public Long roleId;

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	

	// 分页展示资源并根据角色id 查找对应资源加上checked属性 使用选中已经绑定过的资源
	@Action("findByPageByRole")
	public String findByPageByRole() {
		
		Pageable pageable = new PageRequest(getPage()-1, getRows());		
		Specification<Resources> spec = getSpecification();
		// 页面对象--查询所有的资源,并且根据角色已绑定的资源加上checked属性
		Page<Resources> pageBean = resourcesService.findByPage(spec, pageable, roleId);
		// 获取页面数据
		List<Resources> list = pageBean.getContent();
		// 总记录数
		long totalCount = pageBean.getTotalElements();

		result.put("rows", list);
		result.put("total", totalCount);
		
		return writerJson(result);

	}

}
