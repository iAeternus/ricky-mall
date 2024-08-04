package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className PromotionType
 * @desc
 */
public enum PromotionType implements BaseEnum<Short, String> {

    MAX_OF((short) 0, "满减"),
    ZJ((short) 1, "直减"),
    DISCOUNT((short) 2, "折扣"),
    NYG((short) 3, "N元购"),
    ;

    @EnumValue
    final Short key;
    final String val;

    PromotionType(Short key, String val) {
        this.key = key;
        this.val = val;
        initEnum(key, val);
    }
}
