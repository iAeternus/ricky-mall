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
 * @className ImageId
 * @desc
 */
@Value
public class ImageId implements Identifier {

    Long value;

    @JsonCreator
    public ImageId(@JsonProperty("value") Long value) {
        NullException.isNull(value, "图片id不能为空");
        this.value = value;
    }

}
