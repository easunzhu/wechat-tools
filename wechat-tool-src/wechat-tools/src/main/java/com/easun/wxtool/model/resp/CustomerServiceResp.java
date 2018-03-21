package com.easun.wxtool.model.resp;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Create by dongxu.zhu on 2018年3月16日
 */
@XmlRootElement(name = "xml")
public class CustomerServiceResp extends BaseMessageResp {

	private TransInfo transInfo;

	@XmlElement(name = "TransInfo")
	public TransInfo getTransInfo() {
		return transInfo;
	}

	public void setTransInfo(TransInfo transInfo) {
		this.transInfo = transInfo;
	}

}
