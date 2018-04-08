package com.easun.wxtool.common;

import com.alibaba.fastjson.JSONObject;
import com.easun.wxtool.exception.ToolException;
import com.easun.wxtool.log.ToolLog;
import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.pojo.TemplateMessage;
import com.easun.wxtool.util.AccessTokenUtil;
import com.easun.wxtool.util.HttpUtil;

/**
 * 模板消息,对于设置所属行业，查找模板ID，编辑模板格式，请到微信公众平台后台完成
 * 在模版消息发送任务完成后，微信服务器会将是否送达成功作为通知，发送到开发者中心中填写的服务器配置地址中。 如果需要进行处理，请添加相应的处理器
 * 
 * @author dongxu.zhu
 *
 */
public class TemplateMessageUtil {

	/**
	 * 发送模板消息
	 * 
	 * @param message
	 * @return
	 * @throws ToolException
	 */
	public static boolean sendTemplateMsg(TemplateMessage message) throws ToolException {
		ToolLog.log("TemplateMessageUtil.sendTemplateMsg start ...");
		String url = WxConstants.URL_TEMPLATE_SEND.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		String result = HttpUtil.postJson(url, JSONObject.toJSONString(message));
		ToolLog.log("TemplateMessageUtil.sendTemplateMsg result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (WxConstants.RESULT_SUCCESS.equals(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("TemplateMessageUtil.sendTemplateMsg end ...");
		return true;
	}

}
