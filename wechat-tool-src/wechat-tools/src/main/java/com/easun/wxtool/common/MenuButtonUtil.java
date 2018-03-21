package com.easun.wxtool.common;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.easun.wxtool.exception.ToolException;
import com.easun.wxtool.log.ToolLog;
import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.pojo.L1Button;
import com.easun.wxtool.model.pojo.Matchrule;
import com.easun.wxtool.model.pojo.Menu;
import com.easun.wxtool.model.pojo.MenuButton;
import com.easun.wxtool.model.pojo.PersonalityMenu;
import com.easun.wxtool.util.AccessTokenUtil;
import com.easun.wxtool.util.HttpUtil;

/**
 * 自定义菜单，个性化菜单功能类 。具体所需参数及含义，可查询微信官方文档，本工具只是将其封装，方便使用
 * 
 * @author dongxu.zhu
 *
 */
public class MenuButtonUtil {

	/**
	 * 创建普通菜单，菜单顺序为放入list的顺序
	 * 
	 * @param button
	 * @return
	 * @throws ToolException
	 */
	public static boolean createMenu(List<L1Button> button) throws ToolException {
		ToolLog.log("start create menu ...");
		if (null == button || button.size() == 0) {
			throw new NullPointerException("MenuButtonComponents.createMenu button is null ...");
		}
		String url = WxConstants.URL_CREATE_MENU.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		MenuButton menuButton = new MenuButton();
		menuButton.setButton(button);
		String result = HttpUtil.postJson(url, JSONObject.toJSONString(menuButton));
		ToolLog.log("create menu result ==> \n{} ...", result);
		JSONObject jso = JSONObject.parseObject(result);
		String code = jso.getString("errcode");
		if (!"0".equals(code)) {
			throw new ToolException(code, jso.getString("errmsg"));
		}
		ToolLog.log("end create menu ...");
		return true;
	}

	/**
	 * 查询所有菜单，包括个性化菜单
	 * 
	 * @return
	 * @throws ToolException
	 */
	public static Menu getMenu() throws ToolException {
		ToolLog.log("start get menu ...");
		String url = WxConstants.URL_GET_MENU.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		String result = HttpUtil.doGet(url);
		ToolLog.log("get menu result ==> \n{} ...", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (StringUtils.isNotBlank(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("end get menu ...");
		return jso.toJavaObject(Menu.class);
	}

	/**
	 * 删除所有菜单(会同时删除所有个性化菜单)
	 * 
	 * @return
	 * @throws ToolException
	 */
	public static boolean deleteMenu() throws ToolException {
		ToolLog.log("start delete menu ...");
		String url = WxConstants.URL_DELETE_MENU.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		String result = HttpUtil.doGet(url);
		ToolLog.log("delete menu result ==> \n{} ...", result);
		JSONObject jso = JSONObject.parseObject(result);
		String code = jso.getString("errcode");
		if (!"0".equals(code)) {
			throw new ToolException(code, jso.getString("errmsg"));
		}
		ToolLog.log("end delete menu ...");
		return true;
	}

	/**
	 * 创建个性化菜单
	 * 
	 * @param button 菜单,菜单顺序为放入list的顺序
	 * @param matchrule 匹配规则
	 * @return menuid 个性化菜单ID
	 * @throws ToolException
	 */
	public static String createPersonalityMenu(List<L1Button> button, Matchrule matchrule) throws ToolException {
		ToolLog.log("start create personality menu ...");
		if (null == button) {
			throw new NullPointerException("MenuButtonComponents.createPersonalityMenu button is null ...");
		}
		if (null == matchrule) {
			throw new NullPointerException("MenuButtonComponents.createPersonalityMenu matchrule is null ...");
		}
		String url = WxConstants.URL_CREATE_MENU_CONDITIONAL.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		PersonalityMenu personalityMenu = new PersonalityMenu();
		MenuButton menuButton = new MenuButton();
		menuButton.setButton(button);
		personalityMenu.setButton(menuButton.getButton());
		personalityMenu.setMatchrule(matchrule);
		String result = HttpUtil.postJson(url, JSONObject.toJSONString(personalityMenu));
		ToolLog.log("create personality menu result ==> \n{} ...", result);
		JSONObject jso = JSONObject.parseObject(result);
		String code = jso.getString("errcode");
		if (StringUtils.isNotBlank(code)) {
			throw new ToolException(code, jso.getString("errmsg"));
		}
		ToolLog.log("end create personality menu ...");
		return jso.getString("menuid");
	}

	/**
	 * 删除个性化菜单
	 * 
	 * @param menuid
	 * @return
	 * @throws ToolException
	 */
	public static boolean deletePersonalityMenu(String menuid) throws ToolException {
		ToolLog.log("start delete personality menu ...");
		String url = WxConstants.URL_DELETE_MENU_CONDITIONAL.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		String result = HttpUtil.postJson(url, "{\"menuid\":\"" + menuid + "\"}");
		ToolLog.log("delete personality menu result ==> \n{} ...", result);
		JSONObject jso = JSONObject.parseObject(result);
		String code = jso.getString("errcode");
		if (!"0".equals(code)) {
			throw new ToolException(code, jso.getString("errmsg"));
		}
		ToolLog.log("end delete menu ...");
		return true;
	}

}
