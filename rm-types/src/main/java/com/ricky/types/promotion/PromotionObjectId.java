package com.ricky.types.promotion;

import com.ricky.exception.NullException;
import com.ricky.marker.Identifier;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/12
 * @className PromotionObjectId
 * @desc 促销对象id
 */
@Value
public class PromotionObjectId implements Identifier {

    Long value;

    public PromotionObjectId(Long value) {
        NullException.isNull(value, "促销对象id不能为空");
        this.value = value;
    }
}
