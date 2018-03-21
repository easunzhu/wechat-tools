package com.easun.wxtool.handler.message;

import com.easun.wxtool.model.req.MessageReq;

public interface Handler {

	public String handle(MessageReq msg) throws Exception;

}
