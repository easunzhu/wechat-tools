package com.easun.wxtool.handler.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.easun.wxtool.model.req.MessageReq;

/**
 * Create by dongxu.zhu on 2018年3月15日
 */
public class EventHandler {

	private Map<String, Handler> eventHandler;

	public EventHandler() {
		eventHandler = new ConcurrentHashMap<>();
	}

	public EventHandler addEventHandler(String event, Handler handler) {
		eventHandler.put(event, handler);
		return this;
	}

	public Handler getHandler(String event) {
		return eventHandler.get(event);
	}

	public String handle(MessageReq msg) throws Exception {
		Handler handler = getHandler(msg.getEvent());
		if (null == handler) {
			throw new NullPointerException("There are no available processors to deal with {" + msg.getEvent() + "} ...");
		}
		return handler.handle(msg);
	}

}
