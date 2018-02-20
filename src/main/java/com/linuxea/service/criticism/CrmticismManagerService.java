package com.linuxea.service.criticism;

import com.google.common.collect.Maps;
import com.jfinal.plugin.activerecord.Model;
import com.linuxea.utils.IdKits;

import java.util.List;
import java.util.Map;

/**
 * Created by Linuxea on 2017/9/15.
 */
public class CrmticismManagerService {
	
	public static final CrmticismManagerService SERVICE = new CrmticismManagerService();
	
	public Map<String, Object> save(String articleId, String message) {
		Map<String, Object> map = Maps.newHashMap();
		ArticleWithMessage articleWithMessage = new ArticleWithMessage();
		String thisId = IdKits.wantId();
		articleWithMessage.setId(thisId);
		articleWithMessage.setMessage(message);
		articleWithMessage.setArticleId(articleId);
		boolean ok = articleWithMessage.save();
		if (ok) {
			map.put("state", "ok");
			map.put("msg", "save ok");
		} else {
			map.put("state", "notok");
			map.put("msg", "save not ok");
		}
		return map;
	}
	
	/**
	 * 查找某文章的所有评论
	 *
	 * @param articleId
	 */
	public List<ArticleWithMessage> find(String articleId) {
		String sql = "select * from xxx where articleId = ? order by create_time desc;";
		List<ArticleWithMessage> articleWithMessageList
				= ArticleWithMessage.dao.find(sql, articleId);
		return articleWithMessageList;
	}
}

/**
 * 暂时的model为了去掉报错信息而已
 */
class ArticleWithMessage extends Model<ArticleWithMessage> {
	
	public static final ArticleWithMessage dao = new ArticleWithMessage().dao();
	private String id;
	private String articleId;
	private String message;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getArticleId() {
		return articleId;
	}
	
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
