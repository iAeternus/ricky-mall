package com.ricky.types.promotion;

import com.ricky.marker.Identifier;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/3
 * @className PromotionId
 * @desc
 */
@Value
public class PromotionId implements Identifier {

    Long value;

}
