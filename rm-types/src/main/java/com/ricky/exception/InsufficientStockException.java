package com.ricky.exception;

import com.ricky.constants.ExceptionCodeConstant;

public class InsufficientStockException extends BaseException {

    public InsufficientStockException(String message) {
        super(message, ExceptionCodeConstant.INSUFFICIENT_STOCK_EXCEPTION);
    }

    public InsufficientStockException(String message, Throwable cause) {
        super(message, cause, ExceptionCodeConstant.INSUFFICIENT_STOCK_EXCEPTION);
    }

    public InsufficientStockException(Throwable cause) {
        super(cause, ExceptionCodeConstant.INSUFFICIENT_STOCK_EXCEPTION);
    }
}