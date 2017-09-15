package com.linuxea.controller.criticismmanage;

import com.linuxea.controller.base.BaseController;
import com.linuxea.service.criticism.CrmticismManagerService;

import java.util.List;
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

	/**
	 * 获取某文章评论
	 */
	public void find() {
		String articleId = getPara("articleId");
		List<?> articleWithMessageList = crmticismManagerService.find(articleId);
		super.renderJson(articleWithMessageList);
	}


	/**
	 * 评论不删除
	 */
	public void delete() throws IllegalAccessException {
		throw new IllegalAccessException("评论不支持删除");
	}

}
