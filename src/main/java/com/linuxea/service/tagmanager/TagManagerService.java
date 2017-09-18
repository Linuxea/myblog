package com.linuxea.service.tagmanager;

import com.google.common.collect.Lists;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.linuxea.model.Article;
import com.linuxea.model.Tag;
import com.linuxea.utils.IdKits;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class TagManagerService {

	public static final TagManagerService SERVICE = new TagManagerService();

	/**
	 * 按照标签进行分组
	 */
	public static Map<String, List<Record>> group() {

		String sql = "select tag.id as tag_id, tag.name as tag_name," +
				" article.id as article_id," +
				" article.title as article_title ," +
				" article.create_time as article_create_time " +
				" from tag left join article_with_tag " +
				" on tag.id = article_with_tag.tag_id " +
				" left join article " +
				" on article.id = article_with_tag.article_id" +
				" where article.status = 1 ";
		List<Record> list = Db.find(sql);
		return list.stream().collect(Collectors.groupingBy(r -> r.getStr("tag_name")));

	}

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
