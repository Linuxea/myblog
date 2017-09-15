package com.linuxea.controller.criticismmanage;

import com.linuxea.controller.base.BaseController;
import com.linuxea.service.criticism.CrmticismManagerService;

import java.util.Map;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class CrmticismManagerController extends BaseController {

	private static CrmticismManagerService crmticismManagerService = CrmticismManagerService.SERVICE;

	/**
	 * 添加评论
	 */
	public void criticise() {
		String articleId = getPara("articleId");
		String message = getPara("message");
		Map<String, Object> okOrnot = crmticismManagerService.save(articleId, message);
		super.renderJson(okOrnot);
	}

}
