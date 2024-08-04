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
public enum ShippingType implements BaseEnum<Short> {

    EXPRESS_DELIVERY((short) 0, "快递"),
    SELF_PICKUP((short) 1, "自提"),
    ;

    @EnumValue
    final Short code;
    final String msg;

    ShippingType(Short code, String msg) {
        this.code = code;
        this.msg = msg;
        initEnum(code, msg);
    }

}
