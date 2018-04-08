package com.easun.wxtool.model.pojo;

import java.util.List;

/**
 * 菜单对象
 * 
 * @author dongxu.zhu
 *
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
