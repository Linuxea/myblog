package com.linuxea.service.loginmanager;

import com.linuxea.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Linuxea on 2017/9/18.
 */
public class LoginService {
	
	public static final LoginService LOGIN_SERVICE = new LoginService();
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);
	
	private LoginService() {
	}
	
	public boolean login(User user) {
		User getUser =
				User.dao.findFirst("select * from user where user_name = ? and user_password = ?", user.getUserName(), user.getUserPassword());
		return null != getUser;
	}
	
}

