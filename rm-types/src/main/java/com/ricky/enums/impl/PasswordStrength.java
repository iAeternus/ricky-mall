package com.ricky.enums.impl;

import com.ricky.enums.BaseEnum;
import lombok.Getter;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className PasswordStrength
 * @desc
 */
@Getter
public enum PasswordStrength implements BaseEnum {

    VERY_WEAK((short) 0, "非常弱"),
    WEAK((short) 1, "弱"),
    MEDIUM((short) 2, "中等"),
    STRONG((short) 3, "强");

    final Short code;
    final String description;

    PasswordStrength(Short code, String description) {
        this.code = code;
        this.description = description;
    }

}
