package com.easun.wxtool.model.resp;

import javax.xml.bind.annotation.XmlElement;

public class Voice {

	// ͨ通过素材管理接口上传多媒体文件，得到的id
	private String mediaId;

	@XmlElement(name = "MediaId")
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
