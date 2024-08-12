package com.ricky.domain.promotion.model.aggregate;

import com.ricky.enums.impl.PromotionObjectEnum;
import com.ricky.enums.impl.PromotionType;
import com.ricky.marker.Aggregate;
import com.ricky.types.common.DateTimeInfo;
import com.ricky.types.promotion.PromotionId;
import com.ricky.types.promotion.PromotionObjectId;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/3
 * @className Promotion
 * @desc 促销聚合根
 */
@Data
public class Promotion implements Aggregate<PromotionId> {

    /**
     * 标识符
     */
    private PromotionId id;

    /**
     * 促销对象
     */
    private PromotionObjectEnum promotionObject;

    /**
     * 促销对象id
     */
    private PromotionObjectId promotionObjectId;

    /**
     * 促销类型
     */
    private PromotionType type;

    /**
     * 时间信息
     */
    private DateTimeInfo dateTimeInfo;

}
