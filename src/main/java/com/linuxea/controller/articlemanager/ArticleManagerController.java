package com.linuxea.controller.articlemanager;

import com.google.common.collect.Maps;
import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Record;
import com.linuxea.controller.base.BaseController;
import com.linuxea.model.Article;
import com.linuxea.service.articlemanager.AriticleManagerService;
import com.linuxea.service.tagmanager.TagManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 文章管理
 * Created by Linuxea on 2017-09-11.
 */
public class ArticleManagerController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleManagerController.class);

	private static final AriticleManagerService ARITICLE_MANAGER_SERVICE = AriticleManagerService.SERVICE;
	private static final TagManagerService TAG_MANAGER_SERVICE = TagManagerService.SERVICE;

	/**
	 * 主页跳转
	 */
	public void index() {
		super.renderJsp("/plugin/JHtmlArea/edit.jsp");
	}


	/**
	 * 加载一条详情
	 */
	public void loadOne() {
		Map<String, Object> dataMap = Maps.newHashMap();
		Article article = ARITICLE_MANAGER_SERVICE.loadOne(getPara("id"));
		if (article == null) {
			LOGGER.warn("id 为:" + getPara("id") + "的文章不存在的");
			return;// 找不到此文章了
		}
		List<Record> records = TAG_MANAGER_SERVICE.getTagNamesByArticleName(article);
		dataMap.put("article", article);
		dataMap.put("tagNames", records);

		if (getSessionAttr("isLogin") != null) {
			dataMap.put("enableDelete", true);
		} else {
			dataMap.put("enableDelete", false);
		}

		renderJson(dataMap);
	}

	/**
	 * 新增文章
	 */
	public void add() {
		if (super.getSessionAttr("isLogin") == null) {
			LOGGER.warn("请勿跳过登录步骤");
			return;
		}
		Article article = getModel(Article.class);
		String labels = getPara("labels");
		Kv kv = ARITICLE_MANAGER_SERVICE.add(article, labels);
		renderJson(kv);
	}

	/**
	 * 更新文章
	 */
	public void update() {
		if (getSessionAttr("isLogin") == null) {
			LOGGER.error("非登录者不能更新!");
			return;
		}
		ARITICLE_MANAGER_SERVICE.update(getModel(Article.class));
	}

	/**
	 * 删除文章
	 */
	public void delete(@Para("") Article article) {
		if (getSessionAttr("isLogin") == null) {
			LOGGER.error("非登录者不能删除!");
			return;
		}
		boolean result = ARITICLE_MANAGER_SERVICE.delete(article);
		String okOrNot = result ? "ok" : "notok";
		renderJson("rs", okOrNot);
	}

	/**
	 * 列出文章
	 */
	public void find() {
		List<Article> articleList =
				Article.dao.find("select id,title,create_time from article where status = 1 order by create_time desc");
		// 状态为1表示正常状态
		renderJson(articleList);
	}

}
