package com.ricky.exception;


import com.ricky.constants.ExceptionCodeConstant;

public class DbException extends BaseException {

    public DbException(String message) {
        super(message, ExceptionCodeConstant.DB_EXCEPTION);
    }

    public DbException(String message, Throwable cause) {
        super(message, cause, ExceptionCodeConstant.DB_EXCEPTION);
    }

    public DbException(Throwable cause) {
        super(cause, ExceptionCodeConstant.DB_EXCEPTION);
    }
}
