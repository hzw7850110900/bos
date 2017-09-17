package com.itheima.bos.job;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.itheima.bos.service.take_delivery.PromotionService;

public class PromotionJob implements Job {
	
	//注入PromotionService层的方法
	@Resource
	PromotionService promotionService;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		promotionService.updateStatus();		
	}

}
