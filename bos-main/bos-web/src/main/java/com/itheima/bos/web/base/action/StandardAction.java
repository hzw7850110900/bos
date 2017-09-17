package com.itheima.bos.web.base.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Standard;
import com.itheima.bos.service.base.StandardService;
import com.itheima.bos.web.action.BaseAction;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Namespace("/standard")
@ParentPackage("struts-default")
public class StandardAction extends BaseAction<Standard> {
	
	
	StandardService standardService;
		
	@Resource
	public void setStandardService(StandardService standardService) {
		this.standardService = standardService;
		super.setBaseService(standardService);
	}

	@Override
	public Specification<Standard> getSpecification() {
		final Standard standard = getModel();
		// 条件的拼接
		// 条件对象
		return new Specification<Standard>() {
			@Override
			public Predicate toPredicate(Root<Standard> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<>();
				if (standard.getName() != null && !standard.getName().equals("")) {
					preList.add(cb.like(root.get("name").as(String.class), "%" + standard.getName() + "%"));
				}
				if (standard.getMinWeight() != null) {
					preList.add(cb.ge(root.get("minWeight").as(Long.class), standard.getMinWeight()));
				}
				if (standard.getMaxWeight() != null) {
					preList.add(cb.le(root.get("maxWeight").as(Long.class), standard.getMaxWeight()));
				}
				Predicate[] arr = new Predicate[preList.size()];
				return cb.and(preList.toArray(arr));
			}
		};

	}

}
