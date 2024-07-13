package com.ricky.types.commodity;

import com.ricky.marker.ValueObject;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className PromotionInformation
 * @desc 促销信息
 */
@Value
@Builder
public class PromotionInformation implements ValueObject {

    BigDecimal originalPrice; // 原价
    BigDecimal discountPrice; // 折扣价
    Double discountPercent; // 折扣百分比
    LocalDate promotionStartDate; // 促销开始日期
    LocalDate promotionEndDate; // 促销结束日期

    /**
     * 计算折扣价
     */
    public void calculateDiscountPrice() {
        // TODO 调用折扣策略，计算折扣价和折扣百分比
    }

}
