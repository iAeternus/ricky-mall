package com.ricky.types.user;

import com.ricky.exception.NullException;
import com.ricky.marker.Identifier;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className UserId
 * @desc
 */
@Value
public class UserId implements Identifier {

    Long value;

    public UserId(Long value) {
        NullException.isNull(value, "id不能为空");
        this.value = value;
    }

}
