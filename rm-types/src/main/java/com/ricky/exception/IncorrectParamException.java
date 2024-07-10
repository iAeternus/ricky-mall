package com.ricky.exception;

import com.ricky.constants.ExceptionCodeConstant;

public class IncorrectParamException extends BaseException {

    public IncorrectParamException(String message) {
        super(message, ExceptionCodeConstant.INCORRECT_PARAM_EXCEPTION);
    }

    public IncorrectParamException(String message, Throwable cause) {
        super(message, cause, ExceptionCodeConstant.INCORRECT_PARAM_EXCEPTION);
    }

    public IncorrectParamException(Throwable cause) {
        super(cause, ExceptionCodeConstant.INCORRECT_PARAM_EXCEPTION);
    }

}
