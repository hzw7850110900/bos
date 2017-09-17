package com.itheima.test;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.itheima.job.MyJob;

public class CronTiggerTest {

	public static void main(String[] args) throws Exception {
		//创建Job
		JobDetail job=JobBuilder.newJob(MyJob.class).build();
		//创建CronTigger
		/*
		 * 	cronSchedule 设定一个日历任务  通过cron表达式设置
		 *   0/5 * * * * ? * : 含义是 每5秒触发一次 且只在0.5.10的秒数上开始触发
		 * */
		Trigger trigger=TriggerBuilder.newTrigger()
				.startNow().withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? *"))
				.build();
		
		//创建Scheduler
		Scheduler scheduler=StdSchedulerFactory.getDefaultScheduler();
		
		scheduler.scheduleJob(job,trigger);
		
		//开始任务
		scheduler.start();
				
	}
}
