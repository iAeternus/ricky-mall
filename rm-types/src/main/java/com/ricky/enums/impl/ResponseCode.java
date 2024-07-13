package com.ricky.enums.impl;

import com.ricky.enums.BaseEnum;
import lombok.Getter;


@Getter
public enum ResponseCode implements BaseEnum {

    SUCCESS((short) 0, "成功"),
    UN_ERROR((short) -1, "未知失败"),
    ILLEGAL_PARAMETER((short) -2, "非法参数"),
    INDEX_DUP((short) -3, "主键冲突"),
    RATE_LIMITER((short) -4, "请求已被限流，超过限流配置"),
    ;

    final Short code;
    final String description;

    ResponseCode(Short code, String description) {
        this.code = code;
        this.description = description;
    }

}