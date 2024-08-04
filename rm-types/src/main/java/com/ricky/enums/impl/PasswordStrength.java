package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className PasswordStrength
 * @desc
 */
public enum PasswordStrength implements BaseEnum<Short, String> {

    VERY_WEAK((short) 0, "非常弱"),
    WEAK((short) 1, "弱"),
    MEDIUM((short) 2, "中等"),
    STRONG((short) 3, "强");

    @EnumValue
    final Short key;
    final String val;

    PasswordStrength(Short key, String val) {
        this.key = key;
        this.val = val;
        initEnum(key, val);
    }

}
