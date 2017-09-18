package com.linuxea.exception;

/**
 * 类别异常
 * create by linuxea on 2017/9/11 13:15
 **/
public class KindException extends BlogException {

	public KindException() {
	}

	public KindException(String message) {
		super(message);
	}

	public KindException(String message, Throwable cause) {
		super(message, cause);
	}

	public KindException(Throwable cause) {
		super(cause);
	}

	public KindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
