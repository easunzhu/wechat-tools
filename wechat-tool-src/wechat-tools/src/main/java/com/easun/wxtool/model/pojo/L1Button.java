package com.easun.wxtool.model.pojo;

import java.util.List;

public class L1Button {

	private String name;
	private String type;
	private String key;
	private String url;
	private String media_id;
	private String appid;
	private String pagepath;
	private List<L2Button> sub_button;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPagepath() {
		return pagepath;
	}

	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}

	public List<L2Button> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<L2Button> sub_button) {
		this.sub_button = sub_button;
	}

}
