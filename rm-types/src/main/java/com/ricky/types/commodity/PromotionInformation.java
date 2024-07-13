package com.ricky.types.commodity;

import cn.hutool.core.math.Money;
import com.ricky.exception.NullException;
import com.ricky.marker.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    Money originalPrice; // 原价
    Money discountPrice; // 折扣价
    LocalDateTime startTime; // 促销开始时间
    LocalDateTime endTime; // 促销结束时间

    public PromotionInformation(Money originalPrice, Money discountPrice, LocalDateTime startTime, LocalDateTime endTime) {
        NullException.isNull(originalPrice, "原价不能为空");
        NullException.isNull(discountPrice, "折扣价不能为空");
        NullException.isNull(startTime, "促销开始时间不能为空");
        NullException.isNull(endTime, "促销结束时间不能为空");

        this.originalPrice = originalPrice;
        this.discountPrice = discountPrice;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 计算折扣价
     */
    public double calculateDiscountPrice() {
        // TODO 调用折扣策略，计算折扣价和折扣百分比
        return 0;
    }

}
