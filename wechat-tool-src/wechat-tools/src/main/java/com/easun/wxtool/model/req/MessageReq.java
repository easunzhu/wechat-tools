package com.easun.wxtool.model.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class MessageReq {

	private String toUserName;

	private String fromUserName;

	private long createTime;

	// 消息类型（text/event/image/location/link/video/voice/shortvideo）
	private String msgType;

	private long msgId;

	private String content;

	// subscribe(订阅)、unsubscribe(取消订阅),SCAN,LOCATION,CLICK,VIEW
	private String event;

	private String eventKey;

	private String ticket;

	private String latitude;

	private String longitude;

	private String precision;

	public String getToUserName() {
		return toUserName;
	}

	@XmlElement(name = "ToUserName")
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	@XmlElement(name = "FromUserName")
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	@XmlElement(name = "CreateTime")
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	@XmlElement(name = "MsgType")
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public long getMsgId() {
		return msgId;
	}

	@XmlElement(name = "MsgId")
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return content;
	}

	@XmlElement(name = "Content")
	public void setContent(String content) {
		this.content = content;
	}

	public String getEvent() {
		return event;
	}

	@XmlElement(name = "Event")
	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	@XmlElement(name = "EventKey")
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	@XmlElement(name = "Ticket")
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getLatitude() {
		return latitude;
	}

	@XmlElement(name = "Latitude")
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	@XmlElement(name = "Longitude")
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	@XmlElement(name = "Precision")
	public void setPrecision(String precision) {
		this.precision = precision;
	}

	@Override
	public String toString() {
		return "MessageReq [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", msgId=" + msgId + ", content=" + content + ", event=" + event + ", eventKey=" + eventKey + ", ticket=" + ticket + ", latitude="
				+ latitude + ", longitude=" + longitude + ", precision=" + precision + "]";
	}

}
