package com.easun.wxtool.robot;

import com.alibaba.fastjson.JSONObject;
import com.easun.wxtool.log.ToolLog;
import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.util.HttpUtil;

public class Turing {

	private static String turingUrl;

	public static void addApiKey(String apiKey) {
		turingUrl = WxConstants.URL_TURING_ROBOT.replace("apiKey", apiKey);
	}

	public static String tulingAPI(String msg) {
		String url = turingUrl + msg;
		return tulingAPI(url, msg);
	}

	public static String tulingAPI(String url, String msg) {
		ToolLog.log("start call tulingAPI ...");
		String result = HttpUtil.doGet(url);
		ToolLog.log("end call tulingAPI result==> \n{}", result);
		return JSONObject.parseObject(result).getString("text");
	}

}
