package com.nolan.learn.babyrecord.exceptions;

/**
 * description:
 *
 * @author zhun.huang 2019-06-02
 */
public class BusinessException extends RuntimeException {

    private int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
