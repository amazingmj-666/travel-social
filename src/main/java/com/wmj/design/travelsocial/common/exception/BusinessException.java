package com.wmj.design.travelsocial.common.exception;

/**
 * 业务异常
 *
 * @author liumd
 */
public class BusinessException extends RuntimeException {


    protected final String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
