package com.ricky.domain.promotion.model.entity.impl;

import com.ricky.domain.promotion.model.entity.CouponPromotionStrategy;
import com.ricky.types.common.Money;

import java.math.BigDecimal;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className ZJPromotion
 * @desc 直减
 */
public class ZJPromotion implements CouponPromotionStrategy {

    /**
     * 直减值 10 -> 直减10元
     */
    private final Double couponInfo;

    public ZJPromotion(Double couponInfo) {
        this.couponInfo = couponInfo;
    }

    /**
     * 直减计算
     * 1. 使用商品价格减去优惠价格
     * 2. 最低支付金额1元
     */
    @Override
    public Money discountAmount(Money skuPrice) {
        Money discountAmount = skuPrice.subtract(BigDecimal.valueOf(couponInfo));
        if (discountAmount.compareTo(BigDecimal.ZERO) < 1) {
            return new Money(BigDecimal.ONE, skuPrice.getCurrency());
        }
        return discountAmount;
    }
}
