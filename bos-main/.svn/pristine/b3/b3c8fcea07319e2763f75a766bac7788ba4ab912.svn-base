package com.itheima.bos.web.base.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.SubArea;
import com.itheima.bos.service.base.SubAreaService;
import com.itheima.bos.web.action.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/subArea")
@ParentPackage("struts-default")
public class SubAreaAction extends BaseAction<SubArea> {
	
	
	SubAreaService subAreaService;
		
	@Resource
	public void setSubAreaService(SubAreaService subAreaService) {
		this.subAreaService = subAreaService;
		super.setBaseService(subAreaService);
	}

	@Override
	public Specification<SubArea> getSpecification() {
		final SubArea subArea = getModel();
		// 条件的拼接
		// 条件对象
		return new Specification<SubArea>() {
			@Override
			public Predicate toPredicate(Root<SubArea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<>();
				
				if(StringUtils.isNoneBlank(subArea.getKeyWords())){
					preList.add(cb.like(root.get("keyWords").as(String.class), "%"+subArea.getKeyWords()+"%"));
				}
										
				Predicate[] arr = new Predicate[preList.size()];
				return cb.and(preList.toArray(arr));
			}
		};

	}

}
