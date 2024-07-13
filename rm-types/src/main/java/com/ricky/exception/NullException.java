package com.ricky.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ricky.constants.ExceptionCodeConstant;

import java.util.Collection;

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
        if (o == null) {
            throw new NullException(message);
        }
    }

    public static void isNull(String str, String message) {
        if (StrUtil.isBlank(str)) {
            throw new NullException(message);
        }
    }

    public static <T> void isNull(Collection<T> collection, String message) {
        if (CollUtil.isEmpty(collection)) {
            throw new NullException(message);
        }
    }

}
