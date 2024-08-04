package com.ricky.enums.impl;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.ricky.enums.BaseEnum;
import lombok.Getter;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityType
 * @desc 商品状态
 */
public enum CommodityType implements BaseEnum<Short> {

    NOT_LISTED((short) 0, "未上架"),
    LISTED((short) 1, "已上架"),
    ;

    @EnumValue
    final Short code;
    final String msg;

    CommodityType(Short code, String msg) {
        this.code = code;
        this.msg = msg;
        initEnum(code, msg);
    }

}
