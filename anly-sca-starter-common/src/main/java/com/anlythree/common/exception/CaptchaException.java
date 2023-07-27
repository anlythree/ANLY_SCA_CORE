package com.anlythree.common.exception;

/**
 * 验证码异常
 *
 * @author pangu
 */
public class CaptchaException extends AnlyScaException {

	private static final long serialVersionUID = -6550886498142636261L;

	public CaptchaException(String message) {
		super(message);
	}
}
