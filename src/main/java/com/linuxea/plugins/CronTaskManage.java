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


/*
 *@date      2017年3月1日 上午10:40:14
 *@desc      class description
 *@version   1.0
 *@author    linuxea
 */

/**
 * @param <T>
 * @author linuxea.lin
 * @desc 定时任务的管理实现类
 */
public class CronTaskManage implements ICronManage {

	private static final Logger log = LoggerFactory.getLogger(CronTaskManage.class);

	private SchedulerFactory sf = null;
	private Scheduler sched = null;


	@Override
	public void addJob(String cronTime, Job jobclazz) {
		Task task = new Task(cronTime, jobclazz);
		jobLists.add(task);
	}

	@Override
	public void addJob(String taskName, String cronTime, Job jobclazz) {
		Task task = new Task(taskName, cronTime, jobclazz);
		jobLists.add(task);
	}

	@Override
	public void scanPros() {
		jobLists.addAll(CronUtil.scan());
	}


	@Override
	public void startListJob() {
//			Date runTime  = null;
		//log.info("------- Initializing ----------------------");
		// First we must get a reference to a scheduler
		sf = new StdSchedulerFactory();
		try {
			sched = sf.getScheduler();
			// computer a time that is on the next round minute
//			    runTime = evenMinuteDate(new Date());

			// log.info("------- Scheduling Job  -------------------");
			// define the job and tie it to our HelloJob class
		} catch (SchedulerException e1) {
			e1.printStackTrace();
			log.error("scheduler init error:" + e1.getMessage());
		}
		//  log.info("------- Initialization Complete -----------");


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
				sched.scheduleJob(jobDetail, trigger);
			} catch (SchedulerException e1) {
				e1.printStackTrace();
				log.error("job scheduleJob error:" + e1.getMessage());
			}
			//    log.info(jobDetail.getKey() + " will run at: " + runTime);
		}

		try {
			sched.start();
		} catch (SchedulerException e1) {
			e1.printStackTrace();
			log.error("schedule start error" + e1.getMessage());
		}
		//    log.info("------- Started Scheduler -----------------");
	}

	@Override
	public void stop() {
		try {
			sched.clear();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
