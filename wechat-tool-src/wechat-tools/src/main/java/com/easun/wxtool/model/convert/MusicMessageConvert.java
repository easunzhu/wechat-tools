package com.easun.wxtool.model.convert;

import java.util.Date;

import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.req.MessageReq;
import com.easun.wxtool.model.resp.Music;
import com.easun.wxtool.model.resp.MusicMessageResp;
import com.easun.wxtool.util.XmlUtil;

/**
 * 返回音乐消息转换类
 * 
 * @author dongxu.zhu
 *
 */
public class MusicMessageConvert {

	public static String convert(MessageReq msg, Music music) throws Exception {
		if (null == msg) {
			throw new NullPointerException("VoiceMessageConvert.convert error. the MessageReq is null...");
		}
		return convert(msg.getFromUserName(), msg.getToUserName(), music);
	}

	public static String convert(String fromUserName, String toUserName, Music music) throws Exception {
		MusicMessageResp resp = new MusicMessageResp();
		resp.setFromUserName(toUserName);
		resp.setToUserName(fromUserName);
		resp.setMsgType(WxConstants.XML_MUSIC);
		resp.setCreateTime(new Date().getTime());
		resp.setMusic(music);
		return XmlUtil.objToXml(resp);
	}

}
