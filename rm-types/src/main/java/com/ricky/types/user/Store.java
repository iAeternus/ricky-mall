package com.ricky.types.user;

import cn.hutool.core.util.StrUtil;
import com.ricky.exception.NullException;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className Store
 * @desc 店铺
 */
@Value
public class Store implements ValueObject {

    String name; // 店铺名称
    String boss; // 老板名称
    String recordNumber; // 备案号

    public Store(String name, String boss, String recordNumber) {
        NullException.isNull(name, "店铺名称不能为空");
        NullException.isNull(boss, "老板名称不能为空");
        NullException.isNull(recordNumber, "备案号不能为空");

        this.name = name;
        this.boss = boss;
        this.recordNumber = recordNumber;
    }
}
