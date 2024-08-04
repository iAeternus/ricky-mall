package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className WeightUnit
 * @desc 重量单位
 */
public enum WeightUnit implements BaseEnum<String, String> {

    GRAM("g", "克"),
    KILOGRAM("kg", "千克"),
    OUNCE("oz", "盎司"),
    POUND("lb", "磅"),
    ;

    @EnumValue
    final String key;
    final String val;

    WeightUnit(String key, String val) {
        this.key = key;
        this.val = val;
        initEnum(key, val);
    }

    /**
     * 转换为克
     *
     * @param weightValue 重量值
     * @return 转换为克后的重量值
     */
    public double toGrams(double weightValue) {
        return switch (this) {
            case GRAM -> weightValue;
            case KILOGRAM -> weightValue * 1000;
            case OUNCE -> weightValue * 28.35;
            case POUND -> weightValue * 453.59237;
        };
    }

    /**
     * 从克转换
     *
     * @param grams 重量值，单位为克
     * @return 转换为其他单位后的重量值
     */
    public double fromGrams(double grams) {
        return switch (this) {
            case GRAM -> grams;
            case KILOGRAM -> grams / 1000;
            case OUNCE -> grams / 28.35;
            case POUND -> grams / 453.59237;
        };
    }

}