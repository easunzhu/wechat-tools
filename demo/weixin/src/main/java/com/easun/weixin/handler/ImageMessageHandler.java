package com.easun.weixin.handler;

import org.springframework.stereotype.Service;

import com.easun.wxtool.handler.message.Handler;
import com.easun.wxtool.model.convert.TextMessageConvert;
import com.easun.wxtool.model.req.MessageReq;

import lombok.extern.slf4j.Slf4j;

/**
 * Create by dongxu.zhu on 2018年4月8日
 */
@Service("imageMessageHandler")
@Slf4j
public class ImageMessageHandler implements Handler {

	@Override
	public String handle(MessageReq msg) throws Exception {
		log.info("ImageMessageHandler msg==>{}", msg.toString());
		return TextMessageConvert.convert(msg, "这是一张图片!!");
	}

}
