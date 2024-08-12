package com.ricky.types.store;

import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/12
 * @className StoreName
 * @desc 店铺名称
 */
@Value
public class StoreName implements ValueObject {

    String value;

}
