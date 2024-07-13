package com.ricky.types.user;

import com.ricky.marker.Identifier;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className BusinessUserId
 * @desc 商家id
 */
@Value
public class BusinessUserId implements Identifier {

    Long value;

    public BusinessUserId(Long value) {
        if(value == null) {
            throw new IllegalArgumentException("商家id不能为空");
        }
        this.value = value;
    }
}
