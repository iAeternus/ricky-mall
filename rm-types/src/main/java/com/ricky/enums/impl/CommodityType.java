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
@Getter
public enum CommodityType implements BaseEnum {

    NOT_LISTED((short) 0, "未上架"),
    LISTED((short) 1, "已上架"),
    ;

    @EnumValue
    final Short code;
    final String description;

    CommodityType(Short code, String description) {
        this.code = code;
        this.description = description;
    }

}
