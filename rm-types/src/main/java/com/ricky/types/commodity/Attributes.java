package com.ricky.types.commodity;

import com.ricky.marker.ValueObject;
import lombok.Value;

import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className Attributes
 * @desc 商品属性，如颜色：红色，尺寸：XL
 */
@Value
public class Attributes implements ValueObject {

    Map<String, Object> value;

    public Attributes(Map<String, Object> value) {
        this.value = value;
    }

}
