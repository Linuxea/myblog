package com.linuxea.controller.loginmanager;

import com.linuxea.controller.base.BaseController;
import com.linuxea.model.User;
import com.linuxea.service.loginmanager.LoginService;

/**
 * 登录
 * Created by Linuxea on 2017/9/18.
 */
public class LoginController extends BaseController {

	public void index() {
		renderJsp("/login.jsp");
	}

	/**
	 * 登录
	 */
	public void login() {
		User user = getModel(User.class);
		boolean ok = LoginService.LOGIN_SERVICE.login(user);
		if (ok) {
			super.setCookie("isLogin", "ok", 24 * 60 * 60); //有效期为一天
			renderJsp("/plugin/JHtmlArea/edit.jsp");
		} else {
			setAttr("msg", "用户名或者密码有误");
			renderJsp("/login.jsp");
		}
	}

}
