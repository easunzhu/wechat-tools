package com.easun.wxtool.model.resp;

import javax.xml.bind.annotation.XmlElement;

/**
 * Create by dongxu.zhu on 2018年3月15日
 */
public class ArticleItem {

	// 图文消息名称
	private String Title;
	// 图文消息描述
	private String Description;
	// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	private String PicUrl;
	// 点击图文消息跳转链接
	private String Url;

	@XmlElement(name = "Title")
	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	@XmlElement(name = "Description")
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	@XmlElement(name = "PicUrl")
	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	@XmlElement(name = "Url")
	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
