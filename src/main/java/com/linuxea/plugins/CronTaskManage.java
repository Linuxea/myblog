package com.linuxea.plugins;

import com.linuxea.utils.CronUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


/**
 * @author linuxea
 * @version 1.0
 * @date 2017年3月1日 上午10:40:14
 * @desc 定时任务的管理实现类
 */
public class CronTaskManage implements ICronManage {

	private static final Logger log = LoggerFactory.getLogger(CronTaskManage.class);

	private SchedulerFactory sf = null;
	private Scheduler scheduler = null;


	@Override
	public void addJob(String cronTime, Job job) {
		Task task = new Task(cronTime, job);
		jobLists.add(task);
	}

	@Override
	public void addJob(String taskName, String cronTime, Job job) {
		Task task = new Task(taskName, cronTime, job);
		jobLists.add(task);
	}

	@Override
	public void scanPros() {
		jobLists.addAll(CronUtil.scan());
	}


	@Override
	public void startListJob() {
		sf = new StdSchedulerFactory();
		try {
			scheduler = sf.getScheduler();
		} catch (SchedulerException e1) {
			e1.printStackTrace();
			log.error("scheduler init error:" + e1.getMessage());
		}
		for (int i = 0; i < jobLists.size(); i++) {
			Task currentTask = jobLists.get(i);
			Job job = currentTask.getJob();
			String taskName = currentTask.getTaskName();
			JobDetail jobDetail =
					newJob(job.getClass())
							.withIdentity(null == taskName ? "job:" + UUID.randomUUID().toString() : taskName, "group:" + UUID.randomUUID().toString())
							.build();

			CronTrigger trigger =
					newTrigger().withIdentity("trigger:" + UUID.randomUUID().toString(), "group:" + UUID.randomUUID().toString())
							.withSchedule(cronSchedule(currentTask.getTaskCronTime()))
							.build();

			try {
				scheduler.scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e1) {
				e1.printStackTrace();
				log.error("job scheduleJob error:" + e1.getMessage());
			}
		}

		try {
			scheduler.start();
		} catch (SchedulerException e1) {
			e1.printStackTrace();
			log.error("schedule start error" + e1.getMessage());
		}
	}

	@Override
	public void stop() {
		try {
			scheduler.clear();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
