package com.ricky.types.commodity;

import com.ricky.exception.NullException;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityName
 * @desc 商品名
 */
@Value
public class CommodityName implements ValueObject {

    String value;

    public CommodityName(String value) {
        NullException.isNull(value, "商品名不能为空");
        this.value = value;
    }
}
