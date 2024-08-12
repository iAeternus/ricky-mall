package com.ricky.types.commodity;

import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className Stock
 * @desc 商品库存量，默认为0
 */
@Value
@Deprecated
public class Stock implements ValueObject {

    Integer value;

    public Stock(Integer value) {
        if (value == null) {
            value = 0;
        }
        this.value = value;
    }
}
