package com.ricky.exception;

import com.ricky.constants.ExceptionCodeConstant;

public class BadRequestException extends BaseException {

    public BadRequestException(String message) {
        super(message, ExceptionCodeConstant.BAD_REQUEST_EXCEPTION);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause, ExceptionCodeConstant.BAD_REQUEST_EXCEPTION);
    }

    public BadRequestException(Throwable cause) {
        super(cause, ExceptionCodeConstant.BAD_REQUEST_EXCEPTION);
    }
}
