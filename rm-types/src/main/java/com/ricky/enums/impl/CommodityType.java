package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityType
 * @desc 商品状态
 */
public enum CommodityType implements BaseEnum<Short, String> {

    NOT_LISTED((short) 0, "未上架"),
    LISTED((short) 1, "已上架"),
    ;

    @EnumValue
    final Short key;
    final String val;

    CommodityType(Short key, String val) {
        this.key = key;
        this.val = val;
        initEnum(key, val);
    }

}
