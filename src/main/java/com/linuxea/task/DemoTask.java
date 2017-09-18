package com.linuxea.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by Linuxea on 2017/9/18.
 */
public class DemoTask implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("hello quartz!");
	}
}
