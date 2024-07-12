package com.ricky.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseCode {

    SUCCESS(0, "成功"),
    UN_ERROR(-1, "未知失败"),
    ILLEGAL_PARAMETER(-2, "非法参数"),
    INDEX_DUP(-3, "主键冲突"),
    RATE_LIMITER(-4, "请求已被限流，超过限流配置"),
    ;

    private int code;
    private String info;

}