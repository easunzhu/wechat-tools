package com.easun.wxtool.handler.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.easun.wxtool.model.req.MessageReq;

/**
 * Create by dongxu.zhu on 2018年3月15日
 */
public class MessageHandler {

	private Map<String, Handler> messageHandler;

	public MessageHandler() {
		messageHandler = new ConcurrentHashMap<>();
	}

	public MessageHandler addHandler(String msgType, Handler handler) {
		messageHandler.put(msgType, handler);
		return this;
	}

	public Handler getHandler(String msgType) {
		return messageHandler.get(msgType);
	}

	public String handle(MessageReq msg) throws Exception {
		Handler handler = getHandler(msg.getMsgType());
		if (null == handler) {
			throw new NullPointerException("There are no available processors to deal with {" + msg.getMsgType() + "} ...");
		}
		return handler.handle(msg);
	}

}
