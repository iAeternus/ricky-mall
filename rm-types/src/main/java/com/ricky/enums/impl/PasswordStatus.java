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
public enum PasswordStatus implements BaseEnum<Short> {

    UNENCRYPTED((short) 0, "未加密"),
    ENCRYPTED((short) 1, "已加密"),
    NO_ENCRYPTION_REQUIRED((short) 2, "无需加密"),
    ;

    @EnumValue
    final Short code;
    final String msg;

    PasswordStatus(Short code, String msg) {
        this.code = code;
        this.msg = msg;
        initEnum(code, msg);
    }

}
