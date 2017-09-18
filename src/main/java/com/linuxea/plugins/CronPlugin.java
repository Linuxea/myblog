package com.linuxea.plugins;

import com.jfinal.plugin.IPlugin;
import org.quartz.Job;

/**
 *@date      2017年3月1日 上午10:50:49
 *@desc 定时任务插件
 *@version   1.0
 *@author    linuxea
 */
public class CronPlugin implements IPlugin {

	private ICronManage cron = new CronTaskManage();

	@Override
	public boolean start() {
		this.cron.scanPros();//扫描定时任务配置文件
		this.cron.startListJob();
		return true;
	}

	@Override
	public boolean stop() {
		this.cron.stop();
		return true;
	}

	public void addTask(String cronTime, Job jobclazz) {
		this.cron.addJob(null, cronTime, jobclazz);
	}

	public void addTask(String taskName, String cronTime, Job jobclazz) {
		this.cron.addJob(taskName, cronTime, jobclazz);
	}

}
