package com.ricky.exception;

import com.ricky.constants.ExceptionCodeConstant;

public class UnauthorizedException extends BaseException {

    public UnauthorizedException(String message) {
        super(message, ExceptionCodeConstant.UNAUTHORIZED_EXCEPTION);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause, ExceptionCodeConstant.UNAUTHORIZED_EXCEPTION);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause, ExceptionCodeConstant.UNAUTHORIZED_EXCEPTION);
    }
}
