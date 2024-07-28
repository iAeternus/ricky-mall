package com.ricky.types.commodity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricky.exception.NullException;
import com.ricky.marker.Identifier;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/28
 * @className AttributeId
 * @desc
 */
@Value
public class AttributeId implements Identifier {

    Long value;

    @JsonCreator
    public AttributeId(@JsonProperty("value") Long value) {
        NullException.isNull(value, "商品属性ID不能为空");
        this.value = value;
    }
}
