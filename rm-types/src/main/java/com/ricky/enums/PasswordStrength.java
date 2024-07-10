package com.ricky.enums;

import lombok.Getter;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className PasswordStrength
 * @desc
 */
@Getter
public enum PasswordStrength {

    VERY_WEAK((short) 0, "非常弱"),
    WEAK((short) 1, "弱"),
    MEDIUM((short) 2, "中等"),
    STRONG((short) 3, "强");

    final short value;
    final String description;

    PasswordStrength(short value, String description) {
        this.value = value;
        this.description = description;
    }

    public static PasswordStrength of(short index) {
        return switch (index) {
            case 1 -> VERY_WEAK;
            case 2 -> WEAK;
            case 3 -> MEDIUM;
            case 4 -> STRONG;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

}
