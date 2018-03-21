package com.easun.wxtool.model.resp;

import javax.xml.bind.annotation.XmlElement;

/**
 * Create by dongxu.zhu on 2018年3月16日
 */
public class TransInfo {

	private String kfAccount;

	@XmlElement(name = "KfAccount")
	public String getKfAccount() {
		return kfAccount;
	}

	public void setKfAccount(String kfAccount) {
		this.kfAccount = kfAccount;
	}

}
