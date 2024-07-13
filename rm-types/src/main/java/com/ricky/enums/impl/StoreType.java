package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;
import lombok.Getter;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className StoreType
 * @desc 店铺类型
 */
@Getter
public enum StoreType implements BaseEnum {

    SELF_OPERATED_STORE((short) 0, "自营店铺"),
    PINDUOGOU_STORE((short) 1, "拼购店铺"),
    YOUCHUANG_STORE((short) 2, "优创店铺"),
    GLOBAL_SHOPPING_STORES((short) 3, "全球购店铺"),
    POP_STORE((short) 4, "POP店铺"),
    ;

    @EnumValue
    final Short code;
    final String description;

    StoreType(Short code, String description) {
        this.code = code;
        this.description = description;
    }

}
