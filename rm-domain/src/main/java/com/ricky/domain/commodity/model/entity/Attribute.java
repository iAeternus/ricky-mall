package com.ricky.domain.commodity.model.entity;

import com.ricky.exception.NullException;
import com.ricky.marker.Entity;
import com.ricky.types.commodity.AttributeId;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className Attribute
 * @desc 商品属性，如颜色：红色，尺寸：XL
 */
@Data
public class Attribute implements Entity<AttributeId> {

    private AttributeId id;
    private String attributesKey; // 属性键
    private String attributesValue; // 属性值

    public Attribute(AttributeId id, String attributesKey, String attributesValue) {
        NullException.isNull(attributesKey, "商品属性键不能为空");
        NullException.isNull(attributesValue, "商品属性值不能为空");

        this.id = id;
        this.attributesKey = attributesKey;
        this.attributesValue = attributesValue;
    }

}
