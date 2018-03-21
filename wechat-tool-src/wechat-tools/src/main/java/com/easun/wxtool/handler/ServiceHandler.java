package com.easun.wxtool.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create by dongxu.zhu on 2018年3月12日
 */
public interface ServiceHandler {

	public void checkConnect(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public void messageHandle(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
