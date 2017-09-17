package com.itheima.bos.job;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Service;
@Service("jobFactory")
public class JobFactory extends AdaptableJobFactory  {
	
	@Autowired
	private  AutowireCapableBeanFactory capableBeanFactory;

	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		//调用父类的方法 创建job对象
		Object jobInstance = super.createJobInstance(bundle);
		//把创建好的对象交给spring管理
		capableBeanFactory.autowireBean(jobInstance);
		return  jobInstance;
	}
}
