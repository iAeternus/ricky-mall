package com.ricky.domain.promotion.model.aggregate;

import com.ricky.marker.Aggregate;
import com.ricky.types.promotion.PromotionId;
import lombok.Data;

import java.time.LocalDateTime;

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
     * 创建时间
     */
    private LocalDateTime createTime;

}
