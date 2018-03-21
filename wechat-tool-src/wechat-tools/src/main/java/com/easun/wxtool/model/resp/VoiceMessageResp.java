package com.easun.wxtool.model.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class VoiceMessageResp extends BaseMessageResp {

	// 语音
	private Voice voice;

	@XmlElement(name = "Voice")
	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

}
