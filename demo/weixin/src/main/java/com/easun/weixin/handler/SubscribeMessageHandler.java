package com.easun.weixin.handler;

import org.springframework.stereotype.Service;

import com.easun.wxtool.handler.message.Handler;
import com.easun.wxtool.model.convert.TextMessageConvert;
import com.easun.wxtool.model.req.MessageReq;

import lombok.extern.slf4j.Slf4j;

/**
 * Create by dongxu.zhu on 2018年3月15日
 */
@Service("subscribeMessageHandler")
@Slf4j
public class SubscribeMessageHandler implements Handler {

	@Override
	public String handle(MessageReq msg) throws Exception {
		log.info("SubscribeMessageHandler msg==>{}", msg.toString());
		return TextMessageConvert.convert(msg, "欢迎光临~~~ \n 请领取新手大礼包!!!");
	}

}
