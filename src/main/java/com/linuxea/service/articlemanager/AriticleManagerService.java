package com.linuxea.service.articlemanager;

import com.jfinal.kit.Kv;
import com.linuxea.model.Article;
import com.linuxea.model.ArticleWithTag;
import com.linuxea.service.tagmanager.TagManagerService;
import com.linuxea.utils.IdKits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class AriticleManagerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AriticleManagerService.class);

    public static final AriticleManagerService SERVICE = new AriticleManagerService();
	public static final TagManagerService TAG_MANAGER_SERVICE = TagManagerService.SERVICE;

	public Kv add(Article article, String labels) {
		List<String> labelsId = TAG_MANAGER_SERVICE.checkLabels(labels);
		Kv kv = Kv.create();
        article.setCreateTime(new Date());
        article.setId(IdKits.wantId());
        if (article.save()) {
            kv.set("stateCode", "ok");
        } else {
            kv.set("stateCode", "notok");
        }
		bindTag(labelsId, article.getId());
		return kv;
    }


	/**
	 * 标签绑定
	 *
	 * @param labelsId
	 * @param articleId
	 */
	private void bindTag(List<String> labelsId, String articleId) {
		for (String temp : labelsId) {
			String articleWithTagId = IdKits.wantId();
			ArticleWithTag articleWithTag = new ArticleWithTag();
			articleWithTag.setId(articleWithTagId);
			articleWithTag.setTagId(temp);
			articleWithTag.setArticleId(articleId);
			articleWithTag.save();
		}
	}


	public boolean update(Article article) {
		return article.update();
    }

    public boolean delete(Article article) {
		article.setStatus(0);
		boolean ok = article.update();
		if (ok) {
			LOGGER.info("删除了id为:" + article.getId() + " 的文章");
		}
		return ok;
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
