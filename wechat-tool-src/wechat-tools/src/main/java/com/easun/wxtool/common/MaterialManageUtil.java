package com.easun.wxtool.common;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.easun.wxtool.exception.ToolException;
import com.easun.wxtool.log.ToolLog;
import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.pojo.ArticleItem;
import com.easun.wxtool.model.pojo.Articles;
import com.easun.wxtool.model.pojo.MaterialCount;
import com.easun.wxtool.model.pojo.UpdateArticle;
import com.easun.wxtool.util.AccessTokenUtil;
import com.easun.wxtool.util.HttpUtil;

/**
 * 素材管理工具类
 * 
 * @author dongxu.zhu
 *
 */
public class MaterialManageUtil {

	/**
	 * 新增临时素材
	 * 
	 * @param type：图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @param file
	 * @return media_id
	 * @throws ToolException
	 */
	public static String addTemporaryMedia(String type, File file) throws ToolException {
		ToolLog.log("MaterialManageUtil.addTemporaryMedia start ...");
		if (null == file) {
			throw new IllegalArgumentException("file cannot null ...");
		}
		if (!checkMediaType(type)) {
			throw new IllegalArgumentException("illegal media type ...");
		}
		String url = WxConstants.URL_UPLOAD_TEMP_MEDIA.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken()).replace("TYPE", type);
		String result = HttpUtil.postForm(url, file);
		ToolLog.log("MaterialManageUtil.addTemporaryMedia result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (StringUtils.isNotBlank(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("MaterialManageUtil.addTemporaryMedia end ...");
		return jso.getString("media_id");
	}

	/**
	 * 新增永久图文素材
	 * 
	 * @param articles
	 * @return
	 * @throws ToolException
	 */
	public static String addNews(Articles articles) throws ToolException {
		ToolLog.log("MaterialManageUtil.addNews start ...");
		if (null == articles) {
			throw new IllegalArgumentException("articles cannot null ...");
		}
		String url = WxConstants.URL_ADD_NEWS_MEDIA.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		String result = HttpUtil.postJson(url, JSONObject.toJSONString(articles));
		ToolLog.log("MaterialManageUtil.addNews result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (StringUtils.isNotBlank(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("MaterialManageUtil.addNews end ...");
		return jso.getString("media_id");
	}

	/**
	 * 上传图文消息内的图片获取URL
	 * 
	 * @param file
	 * @return 图片url
	 * @throws ToolException
	 */
	public static String uploadimg(File file) throws ToolException {
		ToolLog.log("MaterialManageUtil.uploadimg start ...");
		if (null == file) {
			throw new IllegalArgumentException("file cannot null ...");
		}
		String url = WxConstants.URL_UPLOAD_IMAGE_MEDIA.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		String result = HttpUtil.postForm(url, file);
		ToolLog.log("MaterialManageUtil.uploadimg result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (StringUtils.isNotBlank(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("MaterialManageUtil.uploadimg end ...");
		return jso.getString("url");
	}

	/**
	 * 新增其他类型永久素材
	 * 
	 * @param type
	 * @param file
	 * @return
	 * @throws ToolException
	 */
	public static String addMaterial(String type, File file) throws ToolException {
		ToolLog.log("MaterialManageUtil.addMaterial start ...");
		if (null == file) {
			throw new IllegalArgumentException("file cannot null ...");
		}
		if (!checkMediaType(type)) {
			throw new IllegalArgumentException("illegal media type ...");
		}
		String url = WxConstants.URL_UPLOAD_MATERIAL_MEDIA.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken()).replace("TYPE", type);
		String result = HttpUtil.postForm(url, file);
		ToolLog.log("MaterialManageUtil.addMaterial result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (StringUtils.isNotBlank(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("MaterialManageUtil.addMaterial end ...");
		return jso.getString("media_id");
	}

	/**
	 * 修改永久图文素材
	 * 
	 * @param mediaId 要修改的图文消息的id
	 * @param index 要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
	 * @param articleItem
	 * @return
	 * @throws ToolException
	 */
	public static boolean updateNews(String mediaId, int index, ArticleItem articleItem) throws ToolException {
		ToolLog.log("MaterialManageUtil.updateNews start ...");
		UpdateArticle updateArticle = new UpdateArticle();
		updateArticle.setArticles(articleItem);
		updateArticle.setIndex(index);
		updateArticle.setMedia_id(mediaId);
		String url = WxConstants.URL_UPDATE_NEWS_MEDIA.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		String result = HttpUtil.postJson(url, JSONObject.toJSONString(updateArticle));
		ToolLog.log("MaterialManageUtil.updateNews result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (!WxConstants.RESULT_SUCCESS.equals(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("MaterialManageUtil.updateNews end ...");
		return true;
	}

	/**
	 * 删除永久素材
	 * 
	 * @param mediaId
	 * @return
	 * @throws ToolException
	 */
	public static boolean deleteMaterial(String mediaId) throws ToolException {
		ToolLog.log("MaterialManageUtil.deleteMaterial start ...");
		String url = WxConstants.URL_DELETE_MATERIAL_MEDIA.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		String result = HttpUtil.postJson(url, "{\"media_id\":" + mediaId + "}");
		ToolLog.log("MaterialManageUtil.deleteMaterial result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (!WxConstants.RESULT_SUCCESS.equals(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		ToolLog.log("MaterialManageUtil.deleteMaterial end ...");
		return true;
	}

	/**
	 * 获取永久素材数量
	 * 
	 * @return
	 * @throws ToolException
	 */
	public static MaterialCount getMaterialCount() throws ToolException {
		ToolLog.log("MaterialManageUtil.getMaterialCount start ...");
		String url = WxConstants.URL_GET_MATERIAL_COUNT.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken());
		String result = HttpUtil.doGet(url);
		ToolLog.log("MaterialManageUtil.getMaterialCount result={}", result);
		JSONObject jso = JSONObject.parseObject(result);
		if (StringUtils.isNotBlank(jso.getString("errcode"))) {
			throw new ToolException(jso.getString("errcode"), jso.getString("errmsg"));
		}
		MaterialCount materialCount = jso.toJavaObject(MaterialCount.class);
		ToolLog.log("MaterialManageUtil.getMaterialCount end ...");
		return materialCount;
	}

	/**
	 * 验证素材类型
	 * 
	 * @param type：图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @return
	 */
	private static boolean checkMediaType(String type) {
		if (WxConstants.MEDIA_IMAGE.equals(type) || WxConstants.MEDIA_THUMB.equals(type) || WxConstants.MEDIA_VIDEO.equals(type)
				|| WxConstants.MEDIA_VOICE.equals(type)) {
			return true;
		}
		return false;
	}

}
