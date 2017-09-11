package com.linuxea.exception;

/**
 * 标签异常
 * create by linuxea on 2017/9/11 13:15
 **/
public class TagException extends BlogException {
    public TagException() {
    }

    public TagException(String message) {
        super(message);
    }

    public TagException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagException(Throwable cause) {
        super(cause);
    }

    public TagException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
