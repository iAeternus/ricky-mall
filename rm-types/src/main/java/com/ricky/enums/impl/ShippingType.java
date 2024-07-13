package com.ricky.enums.impl;

import com.ricky.enums.BaseEnum;
import lombok.Getter;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className ShippingType
 * @desc
 */
@Getter
public enum ShippingType implements BaseEnum {

    EXPRESS_DELIVERY((short) 0, "快递"),
    SELF_PICKUP((short) 1, "自提"),
    ;

    final Short code;
    final String description;

    ShippingType(Short code, String description) {
        this.code = code;
        this.description = description;
    }

}
