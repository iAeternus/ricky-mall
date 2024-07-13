package com.ricky.types.user;

import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className Level
 * @desc 等级
 */
@Value
public class Level implements ValueObject {

    Integer value;

    public static final Level ZERO = new Level(0);

    public Level(Integer value) {
        if (value == null) {
            throw new IllegalArgumentException("等级不能为空");
        }
        this.value = value;
    }
}
