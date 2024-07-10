package com.ricky.types;

import cn.hutool.core.util.StrUtil;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className Nickname
 * @desc 昵称
 */
@Value
public class Nickname {

    String value;

    public Nickname(String value) {
        if(StrUtil.isBlank(value)) {
            throw new IllegalArgumentException("昵称不能为空");
        }
        this.value = value;
    }

}
