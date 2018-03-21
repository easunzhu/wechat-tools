package com.easun.wxtool.model.convert;

import java.util.Date;

import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.req.MessageReq;
import com.easun.wxtool.model.resp.TextMessageResp;
import com.easun.wxtool.util.XmlUtil;

/**
 * 返回消息转换类
 * 
 * @author dongxu.zhu
 *
 */
public class TextMessageConvert {

	public static String convert(MessageReq msg, String content) throws Exception {
		if (null == msg) {
			throw new NullPointerException("MessageConvert.textMessageConvert error. the MessageReq is null...");
		}
		return convert(msg.getFromUserName(), msg.getToUserName(), content);
	}

	public static String convert(String fromUserName, String toUserName, String content) throws Exception {
		TextMessageResp resp = new TextMessageResp();
		resp.setContent(content);
		resp.setFromUserName(toUserName);
		resp.setToUserName(fromUserName);
		resp.setMsgType(WxConstants.XML_TEXT);
		resp.setCreateTime(new Date().getTime());
		return XmlUtil.objToXml(resp);
	}

}
