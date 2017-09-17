package com.itheima.bos.service.take_delivery.imp;


import java.util.Date;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.take_delivery.PromotionDao;
import com.itheima.bos.domain.take_delivery.PageBean;
import com.itheima.bos.domain.take_delivery.Promotion;
import com.itheima.bos.service.imp.BaseServiceImp;
import com.itheima.bos.service.take_delivery.PromotionService;

@Service("promotionService")
@Transactional
public class PromotionServiceImp extends BaseServiceImp<Promotion> implements PromotionService {
	
	PromotionDao promotionDao;
	//给父类的baseDao对象赋值
	@Resource
	public void setPromotionDao(PromotionDao promotionDao) {
		this.promotionDao = promotionDao;
		super.setBaseDao(promotionDao);
	}
	//实现宣传任务的分页方法
	@Override
	public PageBean<Promotion> queryByPagePromotion(Integer page, Integer pageSize) {
		//查询条件
		Specification<Promotion>spec=new Specification<Promotion>() {

			@Override
			public Predicate toPredicate(Root<Promotion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 
				return cb.equal(root.get("status").as(String.class), "1");
			}
		};
		Pageable pageable=new PageRequest(page-1, pageSize);
		//调用dao层的分页查询方法
		Page<Promotion> list = promotionDao.findAll(spec, pageable);
		//创建分页对象
		PageBean<Promotion> pageBean=new PageBean<>();
		//设置总页数和数据
		pageBean.setList(list.getContent());
		pageBean.setTotal(list.getTotalElements());
		
		return pageBean;
	}
	@Override
	public void updateStatus() {
		
		promotionDao.updateStatus(new Date());
		
	}		
}
