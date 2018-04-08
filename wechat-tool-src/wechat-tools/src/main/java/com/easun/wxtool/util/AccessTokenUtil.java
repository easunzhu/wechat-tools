package com.easun.wxtool.util;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.easun.wxtool.exception.ToolException;
import com.easun.wxtool.model.WxConfig;
import com.easun.wxtool.model.WxConstants;

public class AccessTokenUtil {

	private static long expiresTime = 0l;
	private static String accessToken;
	private static String accessTokenUrl = null;

	public static void addConfig(WxConfig config) {
		if (StringUtils.isBlank(accessTokenUrl)) {
			synchronized (AccessTokenUtil.class) {
				if (StringUtils.isBlank(accessTokenUrl)) {
					accessTokenUrl = WxConstants.URL_GET_ACCESSTOEKN.replace("APPID", config.getAppId()).replace("APPSECRET", config.getAppSecret());
				}
			}
		}
	}

	public static String getAccessToken() throws ToolException {
		if (StringUtils.isBlank(accessTokenUrl)) {
			throw new NullPointerException("Please called method(AccessTokenUtil.addConfig) first");
		}
		long time = new Date().getTime();
		if (expiresTime <= time) {
			synchronized (AccessTokenUtil.class) {
				if (expiresTime <= time) {
					String result = HttpUtil.doGet(accessTokenUrl);
					JSONObject jsonObject = JSONObject.parseObject(result);
					if (StringUtils.isNotBlank(jsonObject.getString("errcode"))) {
						throw new ToolException(jsonObject.getString("errcode"), jsonObject.getString("errmsg"));
					}
					String token = jsonObject.getString("access_token");
					System.out.println(token);
					accessToken = token;
					expiresTime += time + 7200;
				}
			}
		}
		return accessToken;
	}

	public static void refreshToken() throws ToolException {
		expiresTime = 0l;
		accessToken = null;
		getAccessToken();
	}

}
