package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className PasswordStatus
 * @desc
 */
public enum PasswordStatus implements BaseEnum<Short, String> {

    UNENCRYPTED((short) 0, "未加密"),
    ENCRYPTED((short) 1, "已加密"),
    NO_ENCRYPTION_REQUIRED((short) 2, "无需加密"),
    ;

    @EnumValue
    final Short key;
    final String val;

    PasswordStatus(Short key, String val) {
        this.key = key;
        this.val = val;
        initEnum(key, val);
    }

}
