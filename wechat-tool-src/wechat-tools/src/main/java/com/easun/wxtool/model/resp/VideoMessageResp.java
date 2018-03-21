package com.easun.wxtool.model.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class VideoMessageResp extends BaseMessageResp {

	// 视频
	private Video video;

	@XmlElement(name = "Video")
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

}
