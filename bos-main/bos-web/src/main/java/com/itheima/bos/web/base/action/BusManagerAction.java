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

import com.itheima.bos.domain.base.BusManager;
import com.itheima.bos.service.base.BusManagerService;
import com.itheima.bos.web.action.BaseAction;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Namespace("/busManager")
@ParentPackage("struts-default")
public class BusManagerAction extends BaseAction<BusManager> {
	
	
	private BusManagerService busManagerService;
		
	@Resource
	public void setBusManagerService(BusManagerService busManagerService) {
		this.busManagerService = busManagerService;
		super.setBaseService(busManagerService);
	}


	@Override
	public Specification<BusManager> getSpecification() {
		final BusManager busManager = getModel();
		// 条件的拼接
		// 条件对象
		return new Specification<BusManager>() {
			@Override
			public Predicate toPredicate(Root<BusManager> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<>();
				//线路类型的拼接
				if (busManager.getLineType() != null && !busManager.getLineType().equals("")) {
					preList.add(cb.like(root.get("lineType").as(String.class), "%" + busManager.getLineType() + "%"));
				}
				//车牌号的拼接
				if (busManager.getPlateNumber() != null && !busManager.getPlateNumber().equals("")) {
					preList.add(cb.like(root.get("plateNumber").as(String.class), "%" + busManager.getPlateNumber() + "%"));
				}
				
				Predicate[] arr = new Predicate[preList.size()];
				return cb.and(preList.toArray(arr));
			}
		};

	}

}
