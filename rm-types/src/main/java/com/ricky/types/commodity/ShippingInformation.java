package com.ricky.types.commodity;

import com.ricky.enums.impl.ShippingType;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className ShippingInformation
 * @desc 发货信息
 */
@Value
public class ShippingInformation implements ValueObject {

    Weight weight; // 商品重量
    ShippingType type; // 发货方式

    public ShippingInformation(Weight weight, ShippingType type) {
        this.weight = weight;
        this.type = type;
    }

}
