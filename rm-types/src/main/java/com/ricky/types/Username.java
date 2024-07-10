package com.ricky.types;

import cn.hutool.core.util.StrUtil;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className Username
 * @desc 用户名
 */
@Value
public class Username implements ValueObject {

    String value;

    public Username(String value) {
        if (StrUtil.isBlank(value)) {
            throw new IllegalArgumentException("username不能为空");
        }
        this.value = value;
    }

}
