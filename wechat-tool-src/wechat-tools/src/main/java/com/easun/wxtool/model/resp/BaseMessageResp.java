package com.easun.wxtool.model.resp;

import javax.xml.bind.annotation.XmlElement;

public class BaseMessageResp {

	// 接收方帐号（收到的OpenID）
	private String toUserName;

	// 开发者微信号
	private String fromUserName;

	// 消息创建时间 （整型）
	private long createTime;

	// 消息类型(text/voice/news/video/music/image)
	private String msgType;

	// 位0x0001被标志时，星标刚收到的消息
	private Integer funcFlag;

	@XmlElement(name = "ToUserName")
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	@XmlElement(name = "FromUserName")
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	@XmlElement(name = "CreateTime")
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@XmlElement(name = "MsgType")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@XmlElement(name = "FuncFlag")
	public Integer getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(Integer funcFlag) {
		this.funcFlag = funcFlag;
	}

}
