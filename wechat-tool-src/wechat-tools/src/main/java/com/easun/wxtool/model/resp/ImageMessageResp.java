package com.easun.wxtool.model.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class ImageMessageResp extends BaseMessageResp {

	// ͨ图片
	private Image image;

	@XmlElement(name = "Image")
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
