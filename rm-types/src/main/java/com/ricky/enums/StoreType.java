package com.ricky.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className StoreType
 * @desc 店铺类型
 */
@Getter
public enum StoreType {

    SELF_OPERATED_STORE((short) 0, "自营店铺"),
    PINDUOGOU_STORE((short) 1, "拼购店铺"),
    YOUCHUANG_STORE((short) 2, "优创店铺"),
    GLOBAL_SHOPPING_STORES((short) 3, "全球购店铺"),
    POP_STORE((short) 4, "POP店铺"),
    ;

    @EnumValue
    final short value;
    final String description;

    StoreType(short value, String description) {
        this.value = value;
        this.description = description;
    }

    public static StoreType of(short index) {
        return switch (index) {
            case 1 -> SELF_OPERATED_STORE;
            case 2 -> PINDUOGOU_STORE;
            case 3 -> YOUCHUANG_STORE;
            case 4 -> GLOBAL_SHOPPING_STORES;
            case 5 -> POP_STORE;
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

}
