package com.easun.wxtool.model.pojo;

/**
 * Create by dongxu.zhu on 2018年3月15日
 */
public class Miniprogram {

	// 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）
	private String appid;
	// 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
	private String pagepath;

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

}
