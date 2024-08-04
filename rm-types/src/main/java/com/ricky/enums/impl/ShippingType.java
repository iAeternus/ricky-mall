package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className ShippingType
 * @desc
 */
public enum ShippingType implements BaseEnum<Short, String> {

    EXPRESS_DELIVERY((short) 0, "快递"),
    SELF_PICKUP((short) 1, "自提"),
    ;

    @EnumValue
    final Short key;
    final String val;

    ShippingType(Short key, String val) {
        this.key = key;
        this.val = val;
        initEnum(key, val);
    }

}
