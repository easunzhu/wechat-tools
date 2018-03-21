package com.easun.wxtool.exception;

/**
 * Create by dongxu.zhu on 2018年3月19日
 */
public class ToolException extends Exception {

	private static final long serialVersionUID = 1L;

	private String code;

	public ToolException() {
		super();
	}

	public ToolException(String code, String msg) {
		super(msg);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
