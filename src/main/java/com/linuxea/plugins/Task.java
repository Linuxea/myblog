package com.linuxea.plugins;

import org.quartz.Job;

/**
 * @author linuxea
 * @version 1.0
 * @date 2017年3月1日 下午12:25:49
 * @desc class description
 */

public class Task {
	private String taskName;
	private String taskCronTime;
	private Job job;

	public Task() {
	}

	public Task(String taskCronTime, Job job) {
		this.taskName = null;
		this.taskCronTime = taskCronTime;
		this.job = job;
	}

	public Task(String taskName, String taskCronTime, Job job) {
		this.taskName = taskName;
		this.taskCronTime = taskCronTime;
		this.job = job;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskCronTime() {
		return taskCronTime;
	}

	public void setTaskCronTime(String taskCronTime) {
		this.taskCronTime = taskCronTime;
	}


}
