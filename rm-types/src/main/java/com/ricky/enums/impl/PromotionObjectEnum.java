package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/12
 * @className PromotionObjectEnum
 * @desc
 */
public enum PromotionObjectEnum implements BaseEnum<Short, String> {

    COMMODITY((short) 0, "商品"),
    ORDER((short) 1, "订单"),
    ;

    @EnumValue
    final Short key;
    final String val;

    PromotionObjectEnum(Short key, String val) {
        this.key = key;
        this.val = val;
    }
}
