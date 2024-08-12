package com.ricky.types.store;

import com.ricky.marker.Identifier;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/12
 * @className StoreId
 * @desc
 */
@Value
public class StoreId implements Identifier {

    Long value;

}
