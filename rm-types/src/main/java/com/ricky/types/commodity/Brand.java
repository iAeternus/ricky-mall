package com.ricky.types.commodity;

import cn.hutool.core.util.StrUtil;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className Brand
 * @desc 品牌
 */
@Value
public class Brand implements ValueObject {

    String name; // 品牌名

    public Brand(String name) {
        if(StrUtil.isBlank(name)) {
            throw new IllegalArgumentException("品牌名不能为空");
        }
        this.name = name;
    }
}
