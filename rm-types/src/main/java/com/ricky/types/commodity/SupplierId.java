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
 * @className SupplierId
 * @desc 供应商ID
 */
@Value
public class SupplierId implements Identifier {

    Long value;

    @JsonCreator
    public SupplierId(@JsonProperty("value") Long value) {
        this.value = value;
    }

}
