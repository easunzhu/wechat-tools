package com.easun.wxtool.model.convert;

import java.util.Date;
import java.util.List;

import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.req.MessageReq;
import com.easun.wxtool.model.resp.Article;
import com.easun.wxtool.model.resp.ArticleItem;
import com.easun.wxtool.model.resp.ArticleMessageResp;
import com.easun.wxtool.util.XmlUtil;

/**
 * 返回图文消息转换类
 * 
 * @author dongxu.zhu
 *
 */
public class ArticleMessageConvert {

	public static String convert(MessageReq msg, List<ArticleItem> items) throws Exception {
		if (null == msg) {
			throw new NullPointerException("VoiceMessageConvert.convert error. the MessageReq is null...");
		}
		if (null == items || items.size() == 0) {
			throw new NullPointerException("VoiceMessageConvert.convert error. the items is null...");
		}
		return convert(msg.getFromUserName(), msg.getToUserName(), items);
	}

	public static String convert(String fromUserName, String toUserName, List<ArticleItem> items) throws Exception {
		if (null == items || items.size() == 0) {
			throw new NullPointerException("VoiceMessageConvert.convert error. the items is null...");
		}
		ArticleMessageResp resp = new ArticleMessageResp();
		resp.setFromUserName(toUserName);
		resp.setToUserName(fromUserName);
		resp.setMsgType(WxConstants.XML_NEWS);
		resp.setCreateTime(new Date().getTime());
		resp.setArticleCount(items.size());
		Article article = new Article();
		article.setItem(items);
		resp.setArticle(article);
		return XmlUtil.objToXml(resp);
	}

}
