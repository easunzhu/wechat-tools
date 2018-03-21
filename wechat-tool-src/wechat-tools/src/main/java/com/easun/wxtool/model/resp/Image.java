package com.easun.wxtool.model.resp;

import javax.xml.bind.annotation.XmlElement;

public class Image {

	// ͨ图片消息媒体id
	private String mediaId;

	@XmlElement(name = "MediaId")
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
