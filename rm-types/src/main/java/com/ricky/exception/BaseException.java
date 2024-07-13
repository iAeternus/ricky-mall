package com.ricky.exception;

import lombok.Getter;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className BaseException
 * @desc 异常基类
 */
@Getter
public class BaseException extends RuntimeException {

    private final int code;

    public BaseException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BaseException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public BaseException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

}
