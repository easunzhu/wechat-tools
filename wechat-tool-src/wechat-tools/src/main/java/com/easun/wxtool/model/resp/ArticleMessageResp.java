package com.easun.wxtool.model.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class ArticleMessageResp extends BaseMessageResp {

	// 图文消息个数，限制为10条以内
	private int articleCount;

	// 多条图文消息信息，默认第一个item为大图
	private Article article;

	@XmlElement(name = "ArticleCount")
	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	@XmlElement(name = "Articles")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
