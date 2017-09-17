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
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.service.system.RoleService;
import com.itheima.bos.web.action.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/role")
@ParentPackage("struts-default")
public class RoleAction extends BaseAction<Role> {

	RoleService roleService;

	@Resource
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
		super.setBaseService(roleService);
	}

	@Override
	public Specification<Role> getSpecification() {
		final Role role = getModel();
		// 条件的拼接
		// 条件对象
		return new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<>();

				Predicate[] arr = new Predicate[preList.size()];
				return cb.and(preList.toArray(arr));
			}
		};

	}

	// 接收jueseid
	private Long roleId;
	// 接收所有要绑定资源的id
	private String resId;

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	// 绑定资源
	@Action("bindRes")
	public String bindRes() {
		// 访问业务层
		try {
			roleService.bindResoureces(roleId, resId);
			// 成功
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", e.getMessage());
			e.printStackTrace();
		}

		return writerJson(result);
	}

	// 获取用户id
	private Long userId;

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	// 驱动模型--获取当前页和页面大小
	@Action("findByPageByUser")
	public String findByPageByUser() {
		
		
		
		Pageable pageable = new PageRequest(getPage()-1, getRows());
		Specification<Role> spec = getSpecification();
		// 页面对象--查询所有的资源,并且根据角色已绑定的资源加上checked属性
		Page<Role> pageBean = roleService.findByPage(spec, pageable, userId);
		// 获取页面数据
		List<Role> list = pageBean.getContent();
		// 总记录数
		long totalCount = pageBean.getTotalElements();

		result.put("rows", list);
		result.put("total", totalCount);

		return writerJson(result);

	}

}
