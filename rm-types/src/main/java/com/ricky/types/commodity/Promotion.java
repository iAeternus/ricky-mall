package com.ricky.types.commodity;

import com.ricky.marker.ValueObject;
import com.ricky.types.common.Money;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className Promotion
 * @desc 促销信息
 */
@Value
@Builder
public class Promotion implements ValueObject {

    Money discountPrice; // 折扣价
    LocalDateTime startTime; // 促销开始时间
    LocalDateTime endTime; // 促销结束时间

    public Promotion(Money discountPrice, LocalDateTime startTime, LocalDateTime endTime) {
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
