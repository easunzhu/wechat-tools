package com.easun.wxtool.model.convert;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.model.req.MessageReq;
import com.easun.wxtool.model.resp.CustomerServiceResp;
import com.easun.wxtool.model.resp.TransInfo;
import com.easun.wxtool.util.XmlUtil;

/**
 * Create by dongxu.zhu on 2018年3月16日
 */
public class CustomerServiceMessageConvert {

	public static String convert(MessageReq msg, String kfAccount) throws Exception {
		if (null == msg) {
			throw new NullPointerException("CustomerServiceMessageConvert.convert error. the MessageReq is null...");
		}
		return convert(msg.getFromUserName(), msg.getToUserName(), kfAccount);
	}

	public static String convert(String fromUserName, String toUserName, String kfAccount) throws Exception {
		CustomerServiceResp resp = new CustomerServiceResp();
		resp.setFromUserName(toUserName);
		resp.setToUserName(fromUserName);
		resp.setMsgType(WxConstants.XML_TRANSFER_CUSTOMER_SERVICE);
		resp.setCreateTime(new Date().getTime());
		if (StringUtils.isNotBlank(kfAccount)) {
			TransInfo transInfo = new TransInfo();
			transInfo.setKfAccount(kfAccount);
			resp.setTransInfo(transInfo);
		}
		return XmlUtil.objToXml(resp);
	}

}
