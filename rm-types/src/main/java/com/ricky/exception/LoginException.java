package com.ricky.exception;


import com.ricky.constants.ExceptionCodeConstant;

public class LoginException extends BaseException {

    public LoginException(String message) {
        super(message, ExceptionCodeConstant.LOGIN_EXCEPTION);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause, ExceptionCodeConstant.LOGIN_EXCEPTION);
    }

    public LoginException(Throwable cause) {
        super(cause, ExceptionCodeConstant.LOGIN_EXCEPTION);
    }

}
