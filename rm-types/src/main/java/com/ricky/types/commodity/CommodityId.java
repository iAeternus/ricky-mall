package com.ricky.types.commodity;

import com.ricky.marker.Identifier;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className CommodityId
 * @desc 商品id
 */
@Value
public class CommodityId implements Identifier {

    Long value;

    public CommodityId(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("商品id不能为空");
        }
        this.value = value;
    }
}
