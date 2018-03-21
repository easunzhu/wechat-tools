package com.easun.wxtool.model.pojo;

import java.util.List;

/**
 * Create by dongxu.zhu on 2018年3月20日
 */
public class PersonalityMenu {

	private List<L1Button> button;
	private Matchrule matchrule;
	private String menuid;

	public List<L1Button> getButton() {
		return button;
	}

	public void setButton(List<L1Button> button) {
		this.button = button;
	}

	public Matchrule getMatchrule() {
		return matchrule;
	}

	public void setMatchrule(Matchrule matchrule) {
		this.matchrule = matchrule;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

}
