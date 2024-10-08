package com.ricky.types.user;

import com.ricky.exception.NullException;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className Nickname
 * @desc 昵称
 */
@Value
public class Nickname implements ValueObject {

    String value;

    public Nickname(String value) {
        NullException.isNull(value, "昵称不能为空");
        this.value = value;
    }

}
