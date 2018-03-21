package com.easun.wxtool.model.convert;

import java.util.Date;

import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.req.MessageReq;
import com.easun.wxtool.model.resp.Voice;
import com.easun.wxtool.model.resp.VoiceMessageResp;
import com.easun.wxtool.util.XmlUtil;

/**
 * 返回消息转换类
 * 
 * @author dongxu.zhu
 *
 */
public class VoiceMessageConvert {

	public static String convert(MessageReq msg, Voice voice) throws Exception {
		if (null == msg) {
			throw new NullPointerException("VoiceMessageConvert.convert error. the MessageReq is null...");
		}
		return convert(msg.getFromUserName(), msg.getToUserName(), voice);
	}

	public static String convert(MessageReq msg, String mediaId) throws Exception {
		if (null == msg) {
			throw new NullPointerException("VoiceMessageConvert.convert error. the MessageReq is null...");
		}
		return convert(msg.getFromUserName(), msg.getToUserName(), mediaId);
	}

	public static String convert(String fromUserName, String toUserName, String mediaId) throws Exception {
		Voice voice = new Voice();
		voice.setMediaId(mediaId);
		return convert(fromUserName, toUserName, voice);
	}

	public static String convert(String fromUserName, String toUserName, Voice voice) throws Exception {
		VoiceMessageResp resp = new VoiceMessageResp();
		resp.setFromUserName(toUserName);
		resp.setToUserName(fromUserName);
		resp.setMsgType(WxConstants.XML_VOICE);
		resp.setCreateTime(new Date().getTime());
		resp.setVoice(voice);
		return XmlUtil.objToXml(resp);
	}

}
