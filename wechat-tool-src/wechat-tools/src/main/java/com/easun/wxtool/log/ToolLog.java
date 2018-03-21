package com.easun.wxtool.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create by dongxu.zhu on 2018年3月14日
 */
public class ToolLog {

	private static Logger log = LoggerFactory.getLogger(ToolLog.class);

	private static volatile boolean status = false;

	public static void on_off(boolean flag) {
		status = flag;
	}

	public static void log(String str) {
		if (status) {
			log.info(str);
		}
	}

	public static void log(String str, Object arg) {
		if (status) {
			log.info(str, arg);
		}
	}

	public static void log(String str, Object arg1, Object arg2) {
		if (status) {
			log.info(str, arg1, arg2);
		}
	}

}
