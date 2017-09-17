package com.itheima.bos.dao.take_delivery;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.itheima.bos.dao.BaseDao;
import com.itheima.bos.domain.take_delivery.Promotion;
//宣传任务
public interface PromotionDao extends BaseDao<Promotion> {

	@Query("update Promotion set status='0' where endDate<?")
	@Modifying
	void updateStatus(Date date);
	
	
	
}
