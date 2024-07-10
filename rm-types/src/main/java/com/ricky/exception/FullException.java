package com.ricky.exception;


import com.ricky.constants.ExceptionCodeConstant;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/3
 * @className FullException
 * @desc
 */
public class FullException extends BaseException {

    public FullException(String message) {
        super(message, ExceptionCodeConstant.FULL_EXCEPTION);
    }

    public FullException(String message, Throwable cause) {
        super(message, cause, ExceptionCodeConstant.FULL_EXCEPTION);
    }

    public FullException(Throwable cause) {
        super(cause, ExceptionCodeConstant.FULL_EXCEPTION);
    }

}
