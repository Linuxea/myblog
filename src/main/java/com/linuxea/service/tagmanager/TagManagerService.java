package com.linuxea.service.tagmanager;

import com.google.common.collect.Lists;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.linuxea.model.Article;
import com.linuxea.model.Tag;
import com.linuxea.utils.IdKits;

import java.util.List;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class TagManagerService {

    public static final TagManagerService SERVICE = new TagManagerService();

    public boolean add(Tag tag) {
        return tag.save();
    }

    public boolean update(Tag tag) {
        return tag.update();
    }

    public boolean delete(Tag tag) {
        return tag.delete();
    }

    public void find() {
    }


	/**
	 * 返回标签id
	 *
	 * @param labels
	 */
	public List<String> checkLabels(String labels) {
		List<String> labelsIds = Lists.newArrayList();
		String[] strings = labels.split(",");
		for (String temp : strings) {
			Record record = Db.findFirst("select id from tag where name = ?", temp.trim());
			if (null == record) { //表示还没创建
				String id = IdKits.wantId();
				Tag tag = new Tag();
				tag.setName(temp.trim());
				tag.setId(id);
				tag.save();
				labelsIds.add(id);
			} else {
				labelsIds.add(record.getStr("id"));
			}
		}

		return labelsIds;
	}

	/**
	 * 获取文章的关联tag
	 *
	 * @param article
	 */
	public List<Record> getTagNamesByArticleName(Article article) {
		String sql = "select tag.name\n" +
				"\n" +
				"from article \n" +
				"\n" +
				"left join article_with_tag on article_with_tag.article_id = article.id \n" +
				"\n" +
				"left join tag on tag.id = article_with_tag.tag_id\n" +
				"\n" +
				"where article.id = ?";
		List<Record> tagNameList = Db.find(sql, article.getId());
		return tagNameList;
	}
}
