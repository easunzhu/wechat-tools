package com.easun.wxtool.model.convert;

import java.util.Date;

import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.req.MessageReq;
import com.easun.wxtool.model.resp.Video;
import com.easun.wxtool.model.resp.VideoMessageResp;
import com.easun.wxtool.util.XmlUtil;

/**
 * 返回视频消息转换类
 * 
 * @author dongxu.zhu
 *
 */
public class VideoMessageConvert {

	public static String convert(MessageReq msg, Video video) throws Exception {
		if (null == msg) {
			throw new NullPointerException("VoiceMessageConvert.convert error. the MessageReq is null...");
		}
		return convert(msg.getFromUserName(), msg.getToUserName(), video);
	}

	public static String convert(String fromUserName, String toUserName, Video video) throws Exception {
		VideoMessageResp resp = new VideoMessageResp();
		resp.setFromUserName(toUserName);
		resp.setToUserName(fromUserName);
		resp.setMsgType(WxConstants.XML_VIDEO);
		resp.setCreateTime(new Date().getTime());
		resp.setVideo(video);
		return XmlUtil.objToXml(resp);
	}

}
