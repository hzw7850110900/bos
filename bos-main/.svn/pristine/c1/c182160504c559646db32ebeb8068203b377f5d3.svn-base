package com.itheima.bos.web.base.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.FixedArea;
import com.itheima.bos.service.base.FixedAreaService;
import com.itheima.bos.web.action.BaseAction;
import com.itheima.crm.domain.Customer;


@Controller
@Scope("prototype")
@Namespace("/fixedArea")
@ParentPackage("struts-default")
public class FixedAreaAction extends BaseAction<FixedArea> {
	
	
	FixedAreaService fixedAreaService;
		
	@Resource
	public void setFixedAreaService(FixedAreaService fixedAreaService) {
		this.fixedAreaService = fixedAreaService;
		super.setBaseService(fixedAreaService);
	}

	@Override
	public Specification<FixedArea> getSpecification() {
		final FixedArea fixedArea = getModel();
		// 条件的拼接
		// 条件对象
		return new Specification<FixedArea>() {
			@Override
			public Predicate toPredicate(Root<FixedArea> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<>();
				
				if(StringUtils.isNoneBlank(fixedArea.getFixedAreaName())){
					preList.add(cb.like(root .get("fixedAreaName").as(String.class), "%"+fixedArea.getFixedAreaName()+"%"));
				}
				
				Predicate[] arr = new Predicate[preList.size()];
				return cb.and(preList.toArray(arr));
			}
		};

	}
	//查询没有关联的客户 
	@Action("findNoAssociationCustomer")
	public String findNoAssociationCustomer(){
		
		List<com.itheima.crm.domain.Customer> list = (List<Customer>) WebClient
		.create("http://localhost:9081/crm-web/services/customerService/findNoAssociateCustomers")
		.accept(MediaType.APPLICATION_JSON)
		.getCollection(Customer.class);
		
		return writerJson(list);		
	}
	
	private  Long fixedAreaId;
	
	public void setFixedAreaId(Long fixedAreaId) {
		this.fixedAreaId = fixedAreaId;
	}

	//查询已被某个用户关联的客户
	@Action("findHasAssociationFixedAreaCustomers")
	public String findHasAssociationFixedAreaCustomers(){
		
		List<com.itheima.crm.domain.Customer> list = (List<Customer>) WebClient
		.create("http://localhost:9081/crm-web/services/customerService/findHasAssociateCustomers?fixedAreaId="+fixedAreaId)
		.accept(MediaType.APPLICATION_JSON)
		.getCollection(Customer.class);
		
		return writerJson(list);		
	}
	
	private String customerIds;	
	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}

	//关联客户
	@Action("associateCustomersToFixedArea")
	public String associateCustomersToFixedArea(){
		
		try {
			WebClient
			.create("http://localhost:9081/crm-web/services/customerService/associateCustomersToFixedArea?fixedAreaId="
					+fixedAreaId+"&customerIds="+customerIds)
			.put(null);
			
			result.put("success", true);
		} catch (Exception e) {			
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", e.getMessage());
		}
		
		return writerJson(result);
	}

}
