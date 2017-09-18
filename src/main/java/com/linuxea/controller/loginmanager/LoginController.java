package com.linuxea.controller.loginmanager;

import com.linuxea.controller.base.BaseController;
import com.linuxea.model.User;
import com.linuxea.service.loginmanager.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 登录
 * Created by Linuxea on 2017/9/18.
 */
public class LoginController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

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
			super.setSessionAttr("isLogin", true);
			LOGGER.info(user.getUserName() + "登录成功,ip为;" + super.getRequest().getRemoteAddr());
			forwardAction("/articleController/index"); //跳转到文章创建页面
		} else {
			setAttr("msg", "<p class=\"text-error\">用户名或者密码有误</p>");
			renderJsp("/login.jsp");
			LOGGER.error("ip:" + super.getRequest().getRemoteAddr() + "登录失败");
		}
	}

}
