package com.easun.wxtool.common;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.easun.wxtool.exception.ToolException;
import com.easun.wxtool.log.ToolLog;
import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.pojo.MassFilter;
import com.easun.wxtool.util.AccessTokenUtil;
import com.easun.wxtool.util.HttpUtil;

/**
 * 群发消息工具类
 * 
 * @author dongxu.zhu
 *
 */
public class MassMessageUtil {

	// 防止重复群发标识
	private static volatile String clientmsgidByAll = RandomStringUtils.randomAlphanumeric(12);
	private static volatile String clientmsgidByUser = RandomStringUtils.randomAlphanumeric(12);

	/**
	 * 群发文本消息，根据标签群发
	 * 
	 * @param massFilter 群发标签
	 * @param content 文本内容
	 * @return msg_id 群发消息ID，可用于删除群发，查询群发消息发送状态
	 * @throws ToolException
	 */
	public static String sendAllText(MassFilter massFilter, String content) throws ToolException {
		if (StringUtils.isBlank(content)) {
			throw new IllegalArgumentException("the content cannot null ...");
		}
		return sendAll(massFilter, WxConstants.MASS_TEXT, content);
	}

	/**
	 * 群发媒体消息，根据标签群发
	 * 
	 * @param massFilter 群发标签
	 * @param type 消息类型
	 * @param mediaId 媒体ID，如果消息类型为文本，可传文本内容
	 * @return msg_id 群发消息ID，可用于删除群发，查询群发消息发送状态
	 * @throws ToolException
	 */
	public static String sendAll(MassFilter massFilter, String type, String mediaId) throws ToolException {
		ToolLog.log("MassMessageUtil.sendAll start ...");
		if (!checkMediaType(type)) {
			throw new IllegalArgumentException("illegal message type ...");
		}
		if (StringUtils.isBlank(mediaId)) {
			throw new IllegalArgumentException("the mediaId cannot null ...");
		}
		String url = WxConstants.URL_GROUP_SEND_ALL.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		StringBuilder sb = new StringBuilder();
		sb.append("{\"filter\":").append(JSONObject.toJSONString(massFilter)).append(",\"").append(type).append("\":{\"");
		if (WxConstants.MASS_TEXT.equals(type)) {
			sb.append("content\":\"").append(mediaId).append("\"},");
		} else {
			sb.append("media_id\":\"").append(mediaId).append("\"},");
		}
		sb.append("\"msgtype\":\"").append(type).append("\",\"clientmsgid\":\"").append(clientmsgidByAll).append("\"}");
		ToolLog.log("MassMessageUtil.sendAll the message={}", sb.toString());
		String result = HttpUtil.postJson(url, sb.toString());
		clientmsgidByAll = RandomStringUtils.randomAlphanumeric(12);
		ToolLog.log("MassMessageUtil.sendAll the result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (!WxConstants.RESULT_SUCCESS.equals(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("MassMessageUtil.sendAll end ...");
		return jso.getString("msg_id");
	}

	/**
	 * 群发文本消息，根据openId群发
	 * 
	 * @param users 用户openid集合
	 * @param content 文本内容
	 * @return msg_id 群发消息ID，可用于删除群发，查询群发消息发送状态
	 * @throws ToolException
	 */
	public static String sendUserText(List<String> users, String content) throws ToolException {
		if (StringUtils.isBlank(content)) {
			throw new IllegalArgumentException("the content cannot null ...");
		}
		return sendUser(users, WxConstants.MASS_TEXT, content);
	}

	/**
	 * 群发媒体消息，根据openId群发
	 * 
	 * @param users 用户openid集合
	 * @param type消息类型
	 * @param mediaId 媒体ID，如果消息类型为文本，可传文本内容
	 * @return msg_id 群发消息ID，可用于删除群发，查询群发消息发送状态
	 * @throws ToolException
	 */
	public static String sendUser(List<String> users, String type, String mediaId) throws ToolException {
		ToolLog.log("MassMessageUtil.sendUser start ...");
		if (!checkMediaType(type)) {
			throw new IllegalArgumentException("illegal message type ...");
		}
		if (StringUtils.isBlank(mediaId)) {
			throw new IllegalArgumentException("the mediaId cannot null ...");
		}
		String url = WxConstants.URL_OPENID_SEND_ALL.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		StringBuilder sb = new StringBuilder();
		sb.append("{\"touser\":").append(JSONObject.toJSONString(users)).append(",\"").append(type).append("\":{\"");
		if (WxConstants.MASS_TEXT.equals(type)) {
			sb.append("content\":\"").append(mediaId).append("\"},");
		} else {
			sb.append("media_id\":\"").append(mediaId).append("\"},");
		}
		sb.append("\"msgtype\":\"").append(type).append("\",\"clientmsgid\":\"").append(clientmsgidByUser).append("\"}");
		ToolLog.log("MassMessageUtil.sendUser the message={}", sb.toString());
		String result = HttpUtil.postJson(url, sb.toString());
		clientmsgidByUser = RandomStringUtils.randomAlphanumeric(12);
		ToolLog.log("MassMessageUtil.sendUser the result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (!WxConstants.RESULT_SUCCESS.equals(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("MassMessageUtil.sendUser end ...");
		return jso.getString("msg_id");
	}

	/**
	 * 删除群发消息
	 * 
	 * @param msg_id 群发消息ID
	 * @return
	 * @throws ToolException
	 */
	public static boolean deleteMass(String msg_id) throws ToolException {
		return deleteMass(msg_id, 0);
	}

	/**
	 * 删除群发消息
	 * 
	 * @param msg_id 群发消息ID
	 * @param articleIdx 要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
	 * @return
	 * @throws ToolException
	 */
	public static boolean deleteMass(String msg_id, Integer articleIdx) throws ToolException {
		ToolLog.log("MassMessageUtil.deleteMass start ...");
		if (StringUtils.isBlank(msg_id)) {
			throw new IllegalArgumentException("the msg_id cannot null ...");
		}
		String url = WxConstants.URL_DELETE_SEND_ALL.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		StringBuilder sb = new StringBuilder();
		sb.append("{\"msg_id\":\"").append(msg_id).append("\"");
		if (null == articleIdx) {
			sb.append("}");
		} else {
			sb.append(",\"article_idx\":").append(articleIdx).append("}");
		}
		ToolLog.log("MassMessageUtil.deleteMass the message={}", sb.toString());
		String result = HttpUtil.postJson(url, sb.toString());
		ToolLog.log("MassMessageUtil.deleteMass the result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (!WxConstants.RESULT_SUCCESS.equals(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("MassMessageUtil.deleteMass end ...");
		return true;
	}

	/**
	 * 查询群发消息发送状态
	 * 
	 * @param msgId 群发消息后返回的消息id
	 * @return 消息发送后的状态，SEND_SUCCESS表示发送成功，SENDING表示发送中，SEND_FAIL表示发送失败，DELETE表示已删除
	 * @throws ToolException
	 */
	public static String sendResult(String msg_id) throws ToolException {
		ToolLog.log("MassMessageUtil.sendResult start ...");
		if (StringUtils.isBlank(msg_id)) {
			throw new IllegalArgumentException("the msg_id cannot null ...");
		}
		String url = WxConstants.URL_GET_STATUS_SEND_ALL.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		StringBuilder sb = new StringBuilder();
		sb.append("{\"msg_id\":\"").append(msg_id).append("\"}");
		ToolLog.log("MassMessageUtil.sendResult the message={}", sb.toString());
		String result = HttpUtil.postJson(url, sb.toString());
		ToolLog.log("MassMessageUtil.sendResult the result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (StringUtils.isNotBlank(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("MassMessageUtil.sendResult end ...");
		return jso.getString("msg_status");
	}

	/**
	 * 验证消息类型
	 * 
	 * @param type：群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
	 * @return
	 */
	private static boolean checkMediaType(String type) {
		if (WxConstants.MASS_NEWS.equals(type) || WxConstants.MASS_IMAGE.equals(type) || WxConstants.MASS_TEXT.equals(type)
				|| WxConstants.MASS_VIDEO.equals(type) || WxConstants.MASS_VOICE.equals(type) || WxConstants.MASS_WXCARD.equals(type)) {
			return true;
		}
		return false;
	}

}
