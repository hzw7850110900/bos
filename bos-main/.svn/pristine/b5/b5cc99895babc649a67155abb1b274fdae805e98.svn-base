package com.itheima.bos.web.take_delivery.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.take_delivery.WayBill;
import com.itheima.bos.service.take_delivery.WayBillService;
import com.itheima.bos.web.action.BaseAction;

@Controller
@Scope("prototype")
@Namespace("/wayBill")
@ParentPackage("struts-default")
public class WayBillAction extends BaseAction<WayBill> {

	WayBillService wayBillService;

	@Resource
	public void setWayBillService(WayBillService wayBillService) {
		this.wayBillService = wayBillService;
		super.setBaseService(wayBillService);
	}

	@Override
	public Specification<WayBill> getSpecification() {
		final WayBill wayBill = getModel();
		
		
		return new Specification<WayBill>() {
			@Override
			public Predicate toPredicate(Root<WayBill> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<Predicate>();
				
				if(wayBill.getIsDelete()!=null && !wayBill.getIsDelete().equals("")) {
					preList.add( cb.equal(root.get("isDelete").as(String.class), wayBill.getIsDelete()) );
				}	
				
				if(StringUtils.isNoneBlank(wayBill.getWayBillNum())) {
					preList.add( cb.like(root.get("wayBillNum").as(String.class), "%"+wayBill.getWayBillNum()+"%") );
				}
				
				if(StringUtils.isNoneBlank(wayBill.getSendAddress())) {
					preList.add( cb.like(root.get("sendAddress").as(String.class), "%"+wayBill.getSendAddress()+"%") );
				}
				
				if(StringUtils.isNoneBlank(wayBill.getRecAddress())) {
					preList.add( cb.like(root.get("recAddress").as(String.class), "%"+wayBill.getRecAddress()+"%") );
				}
				
				if(StringUtils.isNoneBlank(wayBill.getSendProNum())) {
					preList.add( cb.equal(root.get("sendProNum").as(String.class), wayBill.getSendProNum()) );
				}
				
				if(StringUtils.isNoneBlank(wayBill.getSignStatus())) {
					preList.add( cb.equal(root.get("signStatus").as(String.class), wayBill.getSignStatus()) );
				}
			
				Predicate[] arr = new Predicate[preList.size()];
				return cb.and(preList.toArray(arr));
			}
		};

	}

	@Action("findBywayBillNum")
	public String findBywayBillNum(){
		//根据运单号查询数据
		WayBill wayBill = wayBillService.findBywayBillNum(getModel().getWayBillNum());
		
		return writerJson(wayBill);
	}
	
	
	private  Long wayBillId;
	public void setWayBillId(Long wayBillId) {
		this.wayBillId = wayBillId;
	}


	//保存	
	@Action(value="saveNormal",results={@Result(name="success",location="/pages/take_delivery/waybill_manage.jsp",type="redirect")})
	public String saveNormal() throws Exception {
		WayBill wayBill = this.getModel();
		//System.out.println(wayBill.getOrder().getId());
		
		//页面不存在订单
		if(wayBill.getOrder().getId()==null){
			//取消WayBill和Order的关联，让WayBill不会因为关联Order失败
			wayBill.setOrder(null);
		}
		//判断页面是否有运单号，且该运单号存在数据库中
		if( wayBill.getWayBillNum()!=null && !wayBill.getWayBillNum().equals("")){
										
			//根据运单号查询运单
			WayBill persistWayBill = wayBillService.findBywayBillNum(wayBill.getWayBillNum());
			if(persistWayBill!=null){
				//数据库存在该运单,更新操作
				wayBill.setId(persistWayBill.getId());
				//wayBill.setOrder(wayBill.getOrder());
				wayBill.setIsDelete("1");
				wayBillService.save(wayBill);
				return SUCCESS;
			}
		}
		
		//新增操作
		//生成运单号
		wayBill.setWayBillNum(UUID.randomUUID().toString());
		wayBill.setIsDelete("1");
		wayBillService.save(wayBill);
		return SUCCESS;
	}
		
	//接收删除的id
	private String delId;	
	public void setDelId(String delId) {
		this.delId = delId;
	}
	/**
	 * 作废
	 * @return
	 */
	@Action("cancel")
	public String cancel() {
		try {
			wayBillService.cancel(delId);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		return writerJson(result);
	}
		
	
	/**
	 * 还原-查询所有为0的运单
	 */
	@Action("listRestore")
	public String listRestore() {
		List<WayBill> list = wayBillService.QueryByIsDelete();
		return writerJson(list);
	}
	
	private String restoreId;
	public void setRestoreId(String restoreId) {
		this.restoreId = restoreId;
	}

	/**
	 * 还原选择的id的运单
	 */
	@Action("restoreWayBill")
	public String restore() {
		
		try {
			wayBillService.updateByRestoreId(restoreId);
			result.put("success", true);
		} catch (Exception e) {			
			e.printStackTrace();
			result.put("success", true);
			result.put("msg", e.getMessage());
		}
		return writerJson(result);
	}
	
	
	
}
