package com.linuxea.controller.articlemanager;

import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.Kv;
import com.linuxea.controller.base.BaseController;
import com.linuxea.model.Article;
import com.linuxea.service.articlemanager.AriticleManagerService;

import java.util.List;

/**
 * 文章管理
 * Created by Linuxea on 2017-09-11.
 */
public class ArticleManagerController extends BaseController {

    private static final AriticleManagerService ARITICLE_MANAGER_SERVICE = AriticleManagerService.SERVICE;

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
        renderJson(article);
    }

    /**
     * 新增文章
     */
	public void add(@Para("") Article article) {
		Kv kv = ARITICLE_MANAGER_SERVICE.add(article);
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
        List<Article> articleList = Article.dao.find("select * from article");
        renderJson(articleList);
    }

}
