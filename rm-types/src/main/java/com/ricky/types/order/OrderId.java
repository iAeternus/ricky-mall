package com.ricky.types.order;

import com.ricky.exception.NullException;
import com.ricky.marker.Identifier;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className OrderId
 * @desc
 */
@Value
public class OrderId implements Identifier {

    Long value;

    public OrderId(Long value) {
        NullException.isNull(value, "订单id不能为空");
        this.value = value;
    }
}
