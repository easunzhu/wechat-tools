package com.easun.wxtool.model.req;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class MessageReq {

	private String toUserName;
	private String fromUserName;
	private long createTime;
	private String msgType;
	private String content;
	private String picUrl;
	private String mediaId;
	private String format;
	private String recognition;
	private String thumbMediaId;
	private String location_X;
	private String location_Y;
	private String scale;
	private String label;
	private String title;
	private String description;
	private String url;
	private Long msgId;
	private String event;
	private String eventKey;
	private String ticket;
	private String latitude;
	private String longitude;
	private String precision;
	private Long massMsgID;
	private String status;

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

	public String getPicUrl() {
		return picUrl;
	}

	@XmlElement(name = "PicUrl")
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	@XmlElement(name = "MediaId")
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	@XmlElement(name = "Format")
	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return recognition;
	}

	@XmlElement(name = "Recognition")
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	@XmlElement(name = "ThumbMediaId")
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getLocation_X() {
		return location_X;
	}

	@XmlElement(name = "Location_X")
	public void setLocation_X(String location_X) {
		this.location_X = location_X;
	}

	public String getLocation_Y() {
		return location_Y;
	}

	@XmlElement(name = "Location_Y")
	public void setLocation_Y(String location_Y) {
		this.location_Y = location_Y;
	}

	public String getScale() {
		return scale;
	}

	@XmlElement(name = "Scale")
	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	@XmlElement(name = "Label")
	public void setLabel(String label) {
		this.label = label;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement(name = "Title")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement(name = "Description")
	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	@XmlElement(name = "Url")
	public void setUrl(String url) {
		this.url = url;
	}

	public Long getMsgId() {
		return msgId;
	}

	@XmlElement(name = "MsgId")
	public void setMsgId(Long msgId) {
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

	public String getStatus() {
		return status;
	}

	@XmlElement(name = "Status")
	public void setStatus(String status) {
		this.status = status;
	}

	public Long getMassMsgID() {
		return massMsgID;
	}

	@XmlElement(name = "MsgID")
	public void setMassMsgID(Long massMsgID) {
		this.massMsgID = massMsgID;
	}

	@Override
	public String toString() {
		return "MessageReq [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", content=" + content + ", picUrl=" + picUrl + ", mediaId=" + mediaId + ", format=" + format + ", recognition=" + recognition
				+ ", thumbMediaId=" + thumbMediaId + ", location_X=" + location_X + ", location_Y=" + location_Y + ", scale=" + scale + ", label="
				+ label + ", title=" + title + ", description=" + description + ", url=" + url + ", msgId=" + msgId + ", event=" + event
				+ ", eventKey=" + eventKey + ", ticket=" + ticket + ", latitude=" + latitude + ", longitude=" + longitude + ", precision=" + precision
				+ ", massMsgID=" + massMsgID + ", status=" + status + "]";
	}

}
