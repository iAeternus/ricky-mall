package com.ricky.domain.promotion.model.entity.impl;

import com.ricky.domain.promotion.model.entity.CouponPromotionStrategy;
import com.ricky.types.common.Money;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className DiscountPromotion
 * @desc 折扣
 */
public class DiscountPromotion implements CouponPromotionStrategy {

    /**
     * 折扣，0.88 -> 88折
     */
    private final Double couponInfo;

    public DiscountPromotion(Double couponInfo) {
        this.couponInfo = couponInfo;
    }

    /**
     * 折扣计算
     * 1. 使用商品价格乘以折扣比例，为最后支付金额
     * 2. 保留两位小数
     * 3. 最低支付金额1元
     */
    @Override
    public Money discountAmount(Money skuPrice) {
        Money discountAmount = skuPrice.multiply(BigDecimal.valueOf(couponInfo));
        if (discountAmount.compareTo(BigDecimal.ZERO) < 1) {
            return new Money(BigDecimal.ONE, skuPrice.getCurrency());
        }
        return discountAmount;
    }

}
