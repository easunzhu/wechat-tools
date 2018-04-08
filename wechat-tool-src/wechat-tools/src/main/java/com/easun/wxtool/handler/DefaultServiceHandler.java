package com.easun.wxtool.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.easun.wxtool.log.ToolLog;
import com.easun.wxtool.model.req.MessageReq;
import com.easun.wxtool.util.CheckUtil;
import com.easun.wxtool.util.WxEncodingUtil;

public class DefaultServiceHandler extends AbstractServiceHandler {

	private DefaultServiceHandler() {}

	private static DefaultServiceHandler serviceHandler = null;

	public static DefaultServiceHandler createService() {
		if (null == serviceHandler) {
			synchronized (DefaultServiceHandler.class) {
				if (null == serviceHandler) {
					serviceHandler = new DefaultServiceHandler();
				}
			}
		}
		return serviceHandler;
	}

	/**
	 * 公众号接入认证
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@Override
	public void checkConnect(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ToolLog.log("start check connect ...");
		if (null == wxConfig) {
			throw new NullPointerException("Please initConfig first ...");
		}

		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (CheckUtil.checkSignature(wxConfig.getToken(), signature, timestamp, nonce)) {
			ToolLog.log("check connect OK ...");
			out.print(echostr);
		}
		out.close();
		ToolLog.log("end check connect ...");
	}

	/**
	 * 公众号消息处理
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@Override
	public void messageHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String str = "";
		try {
			// 时间戳
			String timeStamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
			// 签名串
			String msgSignature = request.getParameter("msg_signature");
			// 加密状态
			String encryptType = request.getParameter("encrypt_type");
			// 获取请求输入流
			InputStream is = request.getInputStream();

			// 获取请求报文
			StringBuilder sb = new StringBuilder();
			byte[] buffer = new byte[1024];
			int read;
			while ((read = is.read(buffer)) != -1) {
				sb.append(new String(buffer, 0, read, "UTF-8"));
			}
			str = sb.toString();
			ToolLog.log("receive user message ... \n{}", str);
			if (null != encryptType && "aes".equals(encryptType)) {
				ToolLog.log("start decrypt message ... ");
				str = WxEncodingUtil.getInstance().decoding(msgSignature, timeStamp, nonce, str);
				ToolLog.log("end decrypt message ... \n{}", str);
			}
			ToolLog.log("start handle message ...");
			str = msgHandle(str);
			ToolLog.log("end handle message ... \n{}", str);
			if (StringUtils.isNotBlank(str)) {
				if (null != encryptType && "aes".equals(encryptType)) {
					ToolLog.log("start encrypt message ...");
					str = WxEncodingUtil.getInstance().encoding(str, timeStamp, nonce);
					ToolLog.log("end encrypt message ... \n{}", str);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			ToolLog.log("messageHandle is error ==> \n", e);
			str = "";
		}
		response.getWriter().print(str);
	}

	@Override
	public String msgHandle(String str) throws Exception {
		MessageReq msg = xmlToMsg(str);
		return handler(msg);
	}

}
