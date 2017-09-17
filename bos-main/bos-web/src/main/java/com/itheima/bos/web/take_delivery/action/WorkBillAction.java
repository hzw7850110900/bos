package com.itheima.bos.web.take_delivery.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.Courier;
import com.itheima.bos.domain.take_delivery.Order;
import com.itheima.bos.domain.take_delivery.WorkBill;
import com.itheima.bos.service.take_delivery.WorkBillService;
import com.itheima.bos.web.action.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/workbill")
@ParentPackage("struts-default")
public class WorkBillAction extends BaseAction<WorkBill> {

	WorkBillService workBillService;

	@Resource
	public void setWorkBillService(WorkBillService workBillService) {
		this.workBillService = workBillService;
		super.setBaseService(workBillService);
	}

	@Override
	public Specification<WorkBill> getSpecification() {
		final WorkBill workBill = getModel();

		return new Specification<WorkBill>() {
			@Override
			public Predicate toPredicate(Root<WorkBill> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<>();
				// 匹配订单号
				if (workBill.getOrder() != null ) {
					if (StringUtils.isNoneBlank(workBill.getOrder().getOrderNum())) {
						// 多表查询 --需求 内连接
						// 获取一个join
						Join<WorkBill, Order> join = root.join("order", JoinType.INNER);
						// 拼接一个条件
						preList.add(cb.like(join.get("orderNum").as(String.class),
								"%" + workBill.getOrder().getOrderNum() + "%"));
					}

				}
				// 根据订单时间
				if (workBill.getBuildtime() != null &&! workBill.getBuildtime().equals("") ) {
					// 获取查询的时间					
		     		preList.add(cb.between(root.get("buildtime").as(Date.class), workBill.getBuildtime(), DateUtils.addDays(workBill.getBuildtime(), 1)));
											
					}
													
				// 根据快递员 多表查询
				if (workBill.getCourier() != null) {
					if(StringUtils.isNoneBlank(workBill.getCourier().getName())){
						// 多表查询 --需求 内连接
						// 获取一个join
						Join<WorkBill, Courier> join = root.join("courier", JoinType.INNER);
						// 拼接一个条件
						preList.add(cb.equal(join.get("id").as(Long.class), workBill.getCourier().getId()));
					}
					
				}

				Predicate[] arr = new Predicate[preList.size()];
				return cb.and(preList.toArray(arr));
			}
		};

	}

}
