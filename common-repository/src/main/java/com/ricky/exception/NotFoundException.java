package com.ricky.exception;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/15
 * @className NotFoundException
 * @desc
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

}
