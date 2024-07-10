package com.ricky.exception;


import com.ricky.constants.ExceptionCodeConstant;

public class ForbiddenException extends BaseException {

    public ForbiddenException(String message) {
        super(message, ExceptionCodeConstant.FORBIDDEN_EXCEPTION);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause, ExceptionCodeConstant.FORBIDDEN_EXCEPTION);
    }

    public ForbiddenException(Throwable cause) {
        super(cause, ExceptionCodeConstant.FORBIDDEN_EXCEPTION);
    }
}
