package com.linuxea.exception;

/**
 * 抽象 异常系统
 * create by linuxea on 2017/9/11 13:16
 **/
public class SystemException extends BlogException {

	public SystemException() {
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
