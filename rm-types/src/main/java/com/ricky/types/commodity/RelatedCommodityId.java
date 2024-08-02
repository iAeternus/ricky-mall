package com.ricky.types.commodity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricky.marker.Identifier;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/29
 * @className RelatedCommodityId
 * @desc
 */
@Value
public class RelatedCommodityId implements Identifier {

    Long value;

    @JsonCreator
    public RelatedCommodityId(@JsonProperty("value") Long value) {
        this.value = value;
    }

}
