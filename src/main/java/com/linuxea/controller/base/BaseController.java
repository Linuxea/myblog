package com.linuxea.controller.base;

import com.jfinal.core.Controller;
import com.linuxea.model.User;

/**
 * base Controller
 * create by linuxea on 2017/9/11 12:58
 **/
public class BaseController extends Controller {

	/**
	 * 获取当前登录人的封装信息
	 *
	 * @return
	 */
	protected User getCurrentUser() {
		return this.getSessionAttr("currentUser");
	}

}
