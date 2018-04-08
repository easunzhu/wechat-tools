package com.easun.wxtool.model.convert;

import java.util.Date;

import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.req.MessageReq;
import com.easun.wxtool.model.resp.Image;
import com.easun.wxtool.model.resp.ImageMessageResp;
import com.easun.wxtool.util.XmlUtil;

/**
 * 返回图片消息转换类
 * 
 * @author dongxu.zhu
 *
 */
public class ImageMessageConvert {

	public static String convert(MessageReq msg, Image image) throws Exception {
		if (null == msg) {
			throw new NullPointerException("ImageMessageConvert.convert error. the MessageReq is null...");
		}
		return convert(msg.getFromUserName(), msg.getToUserName(), image);
	}

	public static String convert(MessageReq msg, String mediaId) throws Exception {
		if (null == msg) {
			throw new NullPointerException("ImageMessageConvert.convert error. the MessageReq is null...");
		}
		return convert(msg.getFromUserName(), msg.getToUserName(), mediaId);
	}

	public static String convert(String fromUserName, String toUserName, String mediaId) throws Exception {
		Image image = new Image();
		image.setMediaId(mediaId);
		return convert(fromUserName, toUserName, image);
	}

	public static String convert(String fromUserName, String toUserName, Image image) throws Exception {
		ImageMessageResp resp = new ImageMessageResp();
		resp.setFromUserName(toUserName);
		resp.setToUserName(fromUserName);
		resp.setMsgType(WxConstants.XML_IMAGE);
		resp.setCreateTime(new Date().getTime());
		resp.setImage(image);
		return XmlUtil.objToXml(resp);
	}

}
