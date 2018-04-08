package com.easun.wxtool.model.pojo;

/**
 * Create by dongxu.zhu on 2018年4月2日
 */
public class UpdateArticle {

	private String media_id;
	private int index;
	private ArticleItem articles;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ArticleItem getArticles() {
		return articles;
	}

	public void setArticles(ArticleItem articles) {
		this.articles = articles;
	}

}
