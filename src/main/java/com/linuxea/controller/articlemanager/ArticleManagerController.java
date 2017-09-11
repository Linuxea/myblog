package com.linuxea.controller.articlemanager;

import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.SqlPara;
import com.linuxea.model.Article;
import com.linuxea.service.articlemanager.AriticleManagerService;

/**
 * 文章管理
 * Created by Linuxea on 2017-09-11.
 */
public class ArticleManagerController extends Controller {

    private static final AriticleManagerService ARITICLE_MANAGER_SERVICE = AriticleManagerService.SERVICE;

    /**
     * 主页跳转
     */
    public void index() {
    }


    /**
     * 新增文章
     */
    public void add() {
        Kv kv = ARITICLE_MANAGER_SERVICE.add(getModel(Article.class, ""));
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
    public void delete() {
        ARITICLE_MANAGER_SERVICE.delete(getModel(Article.class));
    }

    /**
     * 列出文章
     */
    public void find() {
        Article.dao.paginate(0, 10, new SqlPara());
    }

}
