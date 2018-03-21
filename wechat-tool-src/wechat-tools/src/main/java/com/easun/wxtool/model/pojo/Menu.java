package com.easun.wxtool.model.pojo;

import java.util.List;

/**
 * Create by dongxu.zhu on 2018年3月21日
 */
public class Menu {

	private MenuButton menu;
	private List<PersonalityMenu> conditionalmenu;

	public MenuButton getMenu() {
		return menu;
	}

	public void setMenu(MenuButton menu) {
		this.menu = menu;
	}

	public List<PersonalityMenu> getConditionalmenu() {
		return conditionalmenu;
	}

	public void setConditionalmenu(List<PersonalityMenu> conditionalmenu) {
		this.conditionalmenu = conditionalmenu;
	}

}
