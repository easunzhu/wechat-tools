package com.easun.wxtool.model.resp;

import javax.xml.bind.annotation.XmlElement;

public class Video {

	// ͨ通过素材管理接口上传多媒体文件，得到的id
	private String mediaId;
	// 视频消息的标题
	private String title;
	// 视频消息的描述
	private String description;

	@XmlElement(name = "MediaId")
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@XmlElement(name = "Title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
