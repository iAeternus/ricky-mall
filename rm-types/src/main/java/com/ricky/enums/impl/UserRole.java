package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className UserRole
 * @desc
 */
public enum UserRole implements BaseEnum<Short, String> {

    ORDINARY_USERS((short) 0, "普通用户"),
    ENTERPRISE_USERS((short) 1, "企业用户"),
    BUSINESS((short) 2, "商家"),
    LOGISTICS_PARTY((short) 3, "物流方"),
    ADMIN((short) 4, "管理员"),
    ;

    @EnumValue
    final Short key;
    final String val;

    UserRole(Short key, String val) {
        this.key = key;
        this.val = val;
        initEnum(key, val);
    }

}
