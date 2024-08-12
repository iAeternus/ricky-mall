package com.ricky.types.store;

import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/12
 * @className StoreDesc
 * @desc 店铺描述
 */
@Value
public class StoreDesc implements ValueObject {

    String value;

}
