package com.linuxea.controller.loginmanager;

import com.linuxea.controller.base.BaseController;
import com.linuxea.model.BlackList;
import com.linuxea.model.User;
import com.linuxea.service.loginmanager.LoginService;
import com.linuxea.utils.LoginCountUtil;
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

		if (super.getSessionAttr("currentUser") != null) {
			User currentUser = getSessionAttr("currentUser");
			LOGGER.info("当前已经存在登录信息，" + currentUser.getUserName() + ";拒绝重复登录");
			return;
		}

		String ip = super.getRequest().getRemoteAddr();

		BlackList blackList =
				BlackList.dao.findFirst("select count(*) AS SUM from black_list where ip = ?", ip);

		if (blackList.getLong("SUM") > 0) {
			//存在于黑名单中 拒绝请求
			LOGGER.info("黑名单 ip:" + ip + "欲访问!拒绝请求");
			setAttr("msg", "黑名单用户拒绝访问请求");
			renderJsp("/login.jsp");
			return;
		}

		User user = getModel(User.class);
		boolean ok = LoginService.LOGIN_SERVICE.login(user);
		if (ok) {
			super.setSessionAttr("currentUser", user);
			super.setSessionAttr("isLogin", true);
			LoginCountUtil.countSuccess(ip, true);
			LOGGER.info(user.getUserName() + "登录成功,ip为;");
			forwardAction("/articleController/index"); //跳转到文章创建页面
		} else {
			setAttr("msg", "用户名或者密码有误");
			renderJsp("/login.jsp");
			LoginCountUtil.countSuccess(ip, false);
			LOGGER.error("ip:" + ip + "登录失败");
		}
	}

}
