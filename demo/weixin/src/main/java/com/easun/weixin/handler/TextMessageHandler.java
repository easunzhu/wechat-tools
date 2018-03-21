package com.easun.weixin.handler;

import org.springframework.stereotype.Service;

import com.easun.wxtool.handler.message.Handler;
import com.easun.wxtool.model.convert.TextMessageConvert;
import com.easun.wxtool.model.req.MessageReq;
import com.easun.wxtool.robot.Turing;

import lombok.extern.slf4j.Slf4j;

/**
 * Create by dongxu.zhu on 2018年3月14日
 */
@Service("textMessageHandler")
@Slf4j
public class TextMessageHandler implements Handler {

	@Override
	public String handle(MessageReq msg) throws Exception {
		log.info("textMessageHandler msg==>{}", msg.toString());
		String content = Turing.tulingAPI(msg.getContent());
		return TextMessageConvert.convert(msg, content);
	}

}
