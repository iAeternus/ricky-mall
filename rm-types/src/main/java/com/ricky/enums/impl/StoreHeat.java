package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/12
 * @className StoreHeat
 * @desc 店铺热度
 */
public enum StoreHeat implements BaseEnum<Short, String> {

    GENERAL((short) 0, "普通"),
    GOOD((short) 1, "良好"),
    FIERY((short) 2, "火热"),
    ;

    @EnumValue
    final Short key;
    final String val;

    StoreHeat(Short key, String val) {
        this.key = key;
        this.val = val;
    }

}
