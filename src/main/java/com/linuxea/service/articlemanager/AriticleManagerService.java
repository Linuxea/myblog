package com.linuxea.service.articlemanager;

import com.jfinal.kit.Kv;
import com.linuxea.model.Article;
import com.linuxea.utils.IdKits;

import java.util.Date;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class AriticleManagerService {

    public static final AriticleManagerService SERVICE = new AriticleManagerService();

    public Kv add(Article article) {
        Kv kv = Kv.create();
        article.setCreateTime(new Date());
        article.setId(IdKits.wantId());
        if (article.save()) {
            kv.set("stateCode", "ok");
        } else {
            kv.set("stateCode", "notok");
        }
        return kv;
    }

    public boolean update(Article article) {
        return article.update();
    }

    public boolean delete(Article article) {
        return article.delete();
    }

    /**
     * 列出文章每十条
     */
    public void find() {

    }

    /**
     * 加载一条详情
     *
     * @param id
     */
    public Article loadOne(String id) {
        return Article.dao.findById(id);
    }
}
