package com.linuxea.utils;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.linuxea.plugins.Task;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/*
 *@date      2017年3月1日 下午3:12:55
 *@desc      class description
 *@version   1.0
 *@author    linuxea
 */

public class CronUtil {

	private static final Logger log = LoggerFactory.getLogger(CronUtil.class);
	private static final String CRON_PROP_FILE = "cron.properties";
	private static List<Task> taskList = new ArrayList<>();

	public static List<Task> scan() {
		Prop prop = PropKit.use(CRON_PROP_FILE);
		String cronKinds = prop.get("cronKind");
		if (StringUtils.isNotBlank(cronKinds)) {
			String[] classArray = cronKinds.split(",");
			for (String taskKind : classArray) {
				if (StringUtils.isEmpty(taskKind)) {
					continue;//防止配置文件末尾有多个 “,”
				}
				Class<? extends Job> job = null;
				String className = prop.get(taskKind + ".class");
				String cronTime = prop.get(taskKind + ".cronTime");
				String taskName = prop.get(taskKind + ".taskName");
				try {
					job = (Class<? extends Job>) Class.forName(className);
					Constructor constructor =
							job.getDeclaredConstructor();
					Job rs = (Job) constructor.newInstance();
					if (StringUtils.isNotEmpty(taskName)) {
						taskList.add(new Task(taskName, cronTime, rs));
					} else {
						taskList.add(new Task(null, cronTime, rs));
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					log.error(e.getMessage());
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
					log.error(e.getMessage());
				} catch (SecurityException e) {
					e.printStackTrace();
					log.error(e.getMessage());
				} catch (InstantiationException e) {
					e.printStackTrace();
					log.error(e.getMessage());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					log.error(e.getMessage());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
					log.error(e.getMessage());
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					log.error(e.getMessage());
				}

			}

		}
		return taskList;
	}

}
