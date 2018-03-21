package com.easun.wxtool.util;

import org.apache.commons.lang3.StringUtils;

import com.easun.wxtool.exception.AesException;
import com.easun.wxtool.model.WxConfig;
import com.easun.wxtool.util.crypto.WXBizMsgCrypt;

public class WxEncodingUtil {

	private WxEncodingUtil() {}

	private static WxEncodingUtil aesUtil;
	private static WXBizMsgCrypt pc;

	public static WxEncodingUtil getInstance() {
		if (null == aesUtil) {
			synchronized (WxEncodingUtil.class) {
				if (null == aesUtil) {
					aesUtil = new WxEncodingUtil();
				}
			}
		}
		return aesUtil;
	}

	/**
	 * 添加微信参数
	 * 
	 * @param config
	 */
	public void addConfig(WxConfig config) {
		try {
			if (null == pc && StringUtils.isNotBlank(config.getEncodingAesKey())) {
				synchronized (WxEncodingUtil.class) {
					if (null == pc && StringUtils.isNotBlank(config.getEncodingAesKey())) {
						pc = new WXBizMsgCrypt(config.getToken(), config.getEncodingAesKey(), config.getAppId());
					}
				}
			}
		} catch (AesException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 报文加密
	 * 
	 * @param msg 明文
	 * @param timeStamp 时间戳
	 * @param nonce 随机串
	 * @return
	 * @throws Exception
	 */
	public String encoding(String msg, String timeStamp, String nonce) throws AesException {
		if (null == pc) {
			throw new NullPointerException("Please called method(WxEncodingUtil.addConfig) first");
		}
		return pc.encryptMsg(msg, timeStamp, nonce);
	}

	/**
	 * 检验消息的真实性，并且获取解密后的明文
	 * 
	 * @param msgSignature 签名串
	 * @param timeStamp 时间戳
	 * @param nonce 随机串
	 * @param msg 密文
	 * @return
	 * @throws Exception
	 */
	public String decoding(String msgSignature, String timeStamp, String nonce, String msg) throws AesException {
		if (null == pc) {
			throw new NullPointerException("Please called method(WxEncodingUtil.addConfig) first");
		}
		return pc.decryptMsg(msgSignature, timeStamp, nonce, msg);
	}

}
