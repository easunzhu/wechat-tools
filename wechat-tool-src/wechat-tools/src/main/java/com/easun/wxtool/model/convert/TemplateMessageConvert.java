package com.easun.wxtool.model.convert;

import com.alibaba.fastjson.JSONObject;
import com.easun.wxtool.model.pojo.TemplateMessage;

/**
 * 返回模板消息转换类
 * 
 * @author dongxu.zhu
 *
 */
public class TemplateMessageConvert {

	public static String convert(TemplateMessage message) throws Exception {
		return JSONObject.toJSONString(message);
	}

}
