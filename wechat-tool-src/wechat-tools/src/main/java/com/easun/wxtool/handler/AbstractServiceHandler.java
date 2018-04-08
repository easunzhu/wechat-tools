package com.easun.wxtool.handler;

import com.easun.wxtool.handler.message.EventHandler;
import com.easun.wxtool.handler.message.MessageHandler;
import com.easun.wxtool.model.WxConfig;
import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.req.MessageReq;
import com.easun.wxtool.util.AccessTokenUtil;
import com.easun.wxtool.util.WxEncodingUtil;
import com.easun.wxtool.util.XmlUtil;

/**
 * Create by dongxu.zhu on 2018年3月7日
 */
public abstract class AbstractServiceHandler implements ServiceHandler {

	protected WxConfig wxConfig = null;
	protected MessageHandler messageHandler;
	protected EventHandler eventHandler;

	/**
	 * 添加微信公众参数
	 * 
	 * @param config
	 * @return
	 */
	public void initConfig(WxConfig config) {
		this.wxConfig = config;
		WxEncodingUtil.getInstance().addConfig(config);
		AccessTokenUtil.addConfig(config);
	}

	/**
	 * 添加非事件消息处理器
	 * 
	 * @param messageHandler
	 */
	public void addMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	/**
	 * 添加事件消息处理器
	 * 
	 * @param eventHandler
	 */
	public void addEventHandler(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	/**
	 * 消息转换
	 * 
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public MessageReq xmlToMsg(String xml) throws Exception {
		return (MessageReq) XmlUtil.xmlToObj(xml, MessageReq.class);
	}

	/**
	 * 消息处理
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public String handler(MessageReq msg) throws Exception {
		if (WxConstants.XML_EVENT.equals(msg.getMsgType())) {
			if (null == eventHandler) {
				throw new NullPointerException("event message handler is null...");
			}
			return eventHandler.handle(msg);
		} else {
			if (null == messageHandler) {
				throw new NullPointerException("message handler is null...");
			}
			return messageHandler.handle(msg);
		}
	}

	public abstract String msgHandle(String str) throws Exception;

}
