package com.ricky.types.commodity;

import com.ricky.marker.ValueObject;
import com.ricky.types.common.Weight;
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

    public ShippingInformation(Weight weight) {
        this.weight = weight;
    }

}
