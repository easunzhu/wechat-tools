package com.easun.weixin.servlet;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.easun.wxtool.handler.DefaultServiceHandler;
import com.easun.wxtool.handler.message.EventHandler;
import com.easun.wxtool.handler.message.Handler;
import com.easun.wxtool.handler.message.MessageHandler;
import com.easun.wxtool.log.ToolLog;
import com.easun.wxtool.model.WxConfig;
import com.easun.wxtool.model.WxConstants;
import com.easun.wxtool.robot.Turing;

@WebServlet("/coreServlet")
public class CoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ResourceBundle rb = ResourceBundle.getBundle("weixin", Locale.getDefault());

	private DefaultServiceHandler serviceHandler;

	@Override
	public void init() throws ServletException {
		super.init();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		// 设置微信公众号参数
		WxConfig wxConfig = new WxConfig(rb.getString("appId"), rb.getString("appSecret"), rb.getString("token"), rb.getString("encodingAesKey"));
		// 开启框架日志
		ToolLog.on_off(true);
		// 初始化核心处理器
		serviceHandler = DefaultServiceHandler.createService();
		serviceHandler.initConfig(wxConfig);
		// 非事件消息处理器
		MessageHandler messageHandler = new MessageHandler();
		// 事件消息处理器
		EventHandler eventHandler = new EventHandler();
		// 添加消息处理器
		Handler textMessageHandler = (Handler) wac.getBean("textMessageHandler");
		Handler subscribeMessageHandler = (Handler) wac.getBean("subscribeMessageHandler");
		messageHandler.addHandler(WxConstants.XML_TEXT, textMessageHandler);
		eventHandler.addEventHandler(WxConstants.EVT_SUBSCRIBE, subscribeMessageHandler);
		serviceHandler.addMessageHandler(messageHandler);
		serviceHandler.addEventHandler(eventHandler);
		// 初始化图灵机器人
		Turing.addApiKey(rb.getString("Turing_key"));

	}

	/**
	 * 微信接入认证
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		serviceHandler.checkConnect(request, response);
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		serviceHandler.messageHandle(request, response);
	}

}
