package com.ricky.exception;

import com.ricky.constants.ExceptionCodeConstant;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className NullException
 * @desc 空指针异常
 */
public class NullException extends BaseException {

    public NullException(String message) {
        super(message, ExceptionCodeConstant.NULL_EXCEPTION);
    }

    public static void isNull(Object o, String message) {
        if(o == null) {
            throw new NullException(message);
        }
    }

}
