package com.linuxea.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseLoginList<M extends BaseLoginList<M>> extends Model<M> implements IBean {
	
	public java.lang.String getId() {
		return getStr("id");
	}
	
	public void setId(java.lang.String id) {
		set("id", id);
	}
	
	public java.lang.String getIp() {
		return getStr("ip");
	}
	
	public void setIp(java.lang.String ip) {
		set("ip", ip);
	}
	
	public java.lang.Integer getSuccessCount() {
		return getInt("success_count");
	}
	
	public void setSuccessCount(java.lang.Integer successCount) {
		set("success_count", successCount);
	}
	
	public java.lang.Integer getFailCount() {
		return getInt("fail_count");
	}
	
	public void setFailCount(java.lang.Integer failCount) {
		set("fail_count", failCount);
	}
	
}
