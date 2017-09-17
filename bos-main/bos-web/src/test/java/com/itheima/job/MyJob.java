package com.itheima.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob  implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println("任务被触发了,当前的时间为:"+sdf.format(new Date()));
	}

}
