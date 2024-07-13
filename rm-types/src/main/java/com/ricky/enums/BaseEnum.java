package com.ricky.enums;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className BaseEnum
 * @desc 基础枚举接口
 */
public interface BaseEnum {

    /**
     * 获取枚举的值
     * @return 枚举值
     */
    Object getCode();

    /**
     * 获取枚举的描述
     * @return 描述
     */
    String getDescription();

}
