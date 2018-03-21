package com.easun.wxtool.model;

public class WxConfig {

	private String appId;
	private String appSecret;
	private String token;
	private String encodingAesKey;

	public WxConfig(String appId, String appSecret, String token) {
		this(appId, appSecret, token, null);
	}

	public WxConfig(String appId, String appSecret, String token, String encodingAesKey) {
		this.appId = appId;
		this.appSecret = appSecret;
		this.token = token;
		this.encodingAesKey = encodingAesKey;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

}
