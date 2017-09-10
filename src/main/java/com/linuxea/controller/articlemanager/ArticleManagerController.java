package com.linuxea.controller.articlemanager;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.SqlPara;
import com.linuxea.model.Article;
import com.linuxea.service.articlemanager.AriticleManagerService;

/**
 * 文章管理
 * Created by Linuxea on 2017-09-11.
 */
public class ArticleManagerController extends Controller {

    private static final AriticleManagerService ARITICLE_MANAGER_SERVICE = AriticleManagerService.SERVICE;

    public void index() {
    }


    public void add() {
        ARITICLE_MANAGER_SERVICE.add(getModel(Article.class));
    }

    public void update() {
        ARITICLE_MANAGER_SERVICE.update(getModel(Article.class));
    }

    public void delete() {
        ARITICLE_MANAGER_SERVICE.delete(getModel(Article.class));
    }

    public void find() {
        Article.dao.paginate(0, 10, new SqlPara());
    }

}
