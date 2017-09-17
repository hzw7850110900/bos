package com.itheima.test;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.itheima.job.MyJob;

public class SImpleTiggerTest {

	public static void main(String[] args) throws Exception {
		//创建Job
		JobDetail job=JobBuilder.newJob(MyJob.class).build();
		//创建simpleTrigger
		Trigger trigger=TriggerBuilder.newTrigger()
				.startNow().withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(10, 5))
				.build();
		
		//创建Scheduler
		Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();
		
		scheduler.scheduleJob(job,trigger);
		
		//开始任务
		scheduler.start();
				
	}
}
