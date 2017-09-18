package com.linuxea.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Linuxea on 2017/9/18.
 */
public class User extends Model<User> {
	public static final User USER = new User().dao();

	String userName;
	String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}