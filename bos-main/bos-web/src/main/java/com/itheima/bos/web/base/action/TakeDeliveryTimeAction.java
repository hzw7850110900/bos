package com.itheima.bos.web.base.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.itheima.bos.domain.base.TakeDeliveryTime;

import com.itheima.bos.service.base.TakeDeliveryTimeService;
import com.itheima.bos.web.action.BaseAction;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@Namespace("/takeDeliveryTime")
@ParentPackage("struts-default")
public class TakeDeliveryTimeAction extends BaseAction<TakeDeliveryTime> {
	
	
	private TakeDeliveryTimeService takeDeliveryTimeService;
		
	@Resource
	public void setTakeDeliveryTimeService(TakeDeliveryTimeService takeDeliveryTimeService) {
		this.takeDeliveryTimeService = takeDeliveryTimeService;
		super.setBaseService(takeDeliveryTimeService);
	}


	@Override
	public Specification<TakeDeliveryTime> getSpecification() {
		final TakeDeliveryTime takeDeliveryTime = getModel();
		System.out.println(takeDeliveryTime);
		// 条件的拼接
		// 条件对象
		return new Specification<TakeDeliveryTime>() {
			@Override
			public Predicate toPredicate(Root<TakeDeliveryTime> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				// 存放Predicate对象的集合
				List<Predicate> preList = new ArrayList<>();
				//时间名称的拼接
				if (takeDeliveryTime.getTimeName() != null && !takeDeliveryTime.getTimeName().equals("")) {
					preList.add(cb.like(root.get("timeName").as(String.class), "%" + takeDeliveryTime.getTimeName() + "%"));
				}
			
				Predicate[] arr = new Predicate[preList.size()];
				return cb.and(preList.toArray(arr));
			}
		};

	}

	@Override
	@Action("save")
	public String save() throws Exception{
		
		try {
			//得到模型对象
			TakeDeliveryTime time = this.getModel();
			//创建时间格式化类对象
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			//平时上下班时间的对比
			Date datePwt = sdf.parse(time.getPeaWorkingTime());
			Date datePct = sdf.parse(time.getPeaClosingTime());
			int compareToPea = datePwt.compareTo(datePct);
			//周六上下班时间的对比
			Date dateSwt = sdf.parse(time.getStaWorkingTime());
			Date datesct = sdf.parse(time.getStaClosingTime());
			int compareToSta = dateSwt.compareTo(datesct);
			//周日上下班时间的对比
			Date dateSwk = sdf.parse(time.getSunWorkingTime());
			Date dateSck = sdf.parse(time.getSunClosingTime());
			int compareToSun = dateSwk.compareTo(dateSck);
			//通过对的返回的结果判断时间设置是否合理
			if (compareToPea >= 0 || compareToSta >= 0 || compareToSun >= 0) {
				result.put("success", false);
				result.put("message", "下班时间必须大于上班时间");
				return writerJson(result);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return super.save();
	}
}
