package com.ricky.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className UserRole
 * @desc
 */
@Getter
public enum UserRole {

    ORDINARY_USERS((short) 0, "普通用户"),
    ENTERPRISE_USERS((short) 1, "企业用户"),
    BUSINESS((short) 2, "商家"),
    LOGISTICS_PARTY((short) 3, "物流方"),
    ADMIN((short) 4, "管理员"),
    ;

    @EnumValue
    final short value;
    final String description;

    UserRole(short value, String description) {
        this.value = value;
        this.description = description;
    }

    public static UserRole of(short index) {
        return switch (index) {
            case 0 -> ORDINARY_USERS;
            case 1 -> ENTERPRISE_USERS;
            case 3 -> BUSINESS;
            case 4 -> LOGISTICS_PARTY;
            case 5 -> ADMIN;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

}
