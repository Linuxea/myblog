package com.linuxea.controller.articlemanager;

import com.google.common.collect.Maps;
import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Record;
import com.linuxea.controller.base.BaseController;
import com.linuxea.model.Article;
import com.linuxea.service.articlemanager.AriticleManagerService;
import com.linuxea.service.tagmanager.TagManagerService;

import java.util.List;
import java.util.Map;

/**
 * 文章管理
 * Created by Linuxea on 2017-09-11.
 */
public class ArticleManagerController extends BaseController {

    private static final AriticleManagerService ARITICLE_MANAGER_SERVICE = AriticleManagerService.SERVICE;
	private static final TagManagerService TAG_MANAGER_SERVICE = TagManagerService.SERVICE;

	/**
     * 主页跳转
     */
    public void index() {
		super.renderJsp("/plugin/JHtmlArea/edit.html");
	}


    /**
     * 加载一条详情
     */
    public void loadOne() {
        Article article = ARITICLE_MANAGER_SERVICE.loadOne(getPara("id"));
		List<Record> records = TAG_MANAGER_SERVICE.getTagNamesByArticleName(article);
		Map<String, Object> dataMap = Maps.newHashMap();
		dataMap.put("article", article);
		dataMap.put("tagNames", records);
		renderJson(dataMap);
	}

    /**
     * 新增文章
     */
	public void add(Article article) {
		String labels = getPara("labels");
		Kv kv = ARITICLE_MANAGER_SERVICE.add(article, labels);
		renderJson(kv);
    }

    /**
     * 更新文章
     */
    public void update() {
        ARITICLE_MANAGER_SERVICE.update(getModel(Article.class));
    }

    /**
     * 删除文章
     */
	public void delete(@Para("") Article article) {
		ARITICLE_MANAGER_SERVICE.delete(article);
	}

    /**
     * 列出文章
     */
    public void find() {
        //Article.dao.paginate(0, 10, new SqlPara());
		List<Article> articleList = Article.dao.find("select * from article order by create_time desc");
		renderJson(articleList);
    }

}
