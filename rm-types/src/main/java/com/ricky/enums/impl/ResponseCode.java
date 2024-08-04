package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;


public enum ResponseCode implements BaseEnum<Short, String> {

    SUCCESS((short) 0, "成功"),
    UN_ERROR((short) -1, "未知失败"),
    ILLEGAL_PARAMETER((short) -2, "非法参数"),
    INDEX_DUP((short) -3, "主键冲突"),
    RATE_LIMITER((short) -4, "请求已被限流，超过限流配置"),
    ;

    @EnumValue
    final Short key;
    final String val;

    ResponseCode(Short key, String msg) {
        this.key = key;
        this.val = msg;
        initEnum(key, msg);
    }

}