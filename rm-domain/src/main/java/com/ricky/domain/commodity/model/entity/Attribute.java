package com.ricky.domain.commodity.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricky.exception.NullException;
import com.ricky.marker.Entity;
import com.ricky.types.commodity.AttributeId;
import lombok.Builder;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className Attribute
 * @desc 商品属性，如颜色：红色，尺寸：XL
 */
@Data
@Builder
public class Attribute implements Entity<AttributeId> {

    private AttributeId id;
    private String attributeKey; // 属性键
    private String attributeValue; // 属性值

    @JsonCreator
    public Attribute(@JsonProperty("id") AttributeId id,
                     @JsonProperty("attributeKey") String attributeKey,
                     @JsonProperty("attributeValue") String attributeValue) {
        NullException.isNull(attributeKey, "商品属性键不能为空");
        NullException.isNull(attributeValue, "商品属性值不能为空");

        this.id = id;
        this.attributeKey = attributeKey;
        this.attributeValue = attributeValue;
    }

}
