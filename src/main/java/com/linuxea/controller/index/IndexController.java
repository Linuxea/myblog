package com.linuxea.controller.index;

import com.linuxea.controller.base.BaseController;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class IndexController extends BaseController {

    public void index() {
        renderJsp("/index.jsp");
    }

    public void create() {
		if (super.getCookie("isLogin") == null) {
			super.renderJsp("/login.jsp");
		} else {
			super.renderJsp("/plugin/JHtmlArea/edit.jsp");
		}
	}




}
