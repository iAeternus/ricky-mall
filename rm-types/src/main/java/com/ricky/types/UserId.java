package com.ricky.types;

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

    public UserId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id不能为空");
        }
        this.value = id;
    }

}
