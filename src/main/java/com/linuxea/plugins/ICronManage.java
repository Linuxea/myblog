package com.linuxea.plugins;

import org.quartz.Job;

import java.util.ArrayList;
import java.util.List;


/*
 *@date      2017年3月1日 上午10:44:33
 *@desc      class description
 *@version   1.0
 *@author    linuxea
 */

/**
 * @author linuxea.lin
 * @desc 定时任务管理接口
 */
public interface ICronManage {

	List<Task> jobLists = new ArrayList<>();

	void startListJob();

	void addJob(String cronTime, Job jobclazz);

	void addJob(String taskName, String cronTime, Job jobclazz);

	void scanPros();

	void stop();
}
