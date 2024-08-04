package com.ricky.domain.promotion.model.entity.impl;

import com.ricky.domain.promotion.model.entity.CouponPromotionStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className DiscountPromotion
 * @desc 折扣
 */
public class DiscountPromotion implements CouponPromotionStrategy<Double> {

    /**
     * 折扣计算
     * 1. 使用商品价格乘以折扣比例，为最后支付金额
     * 2. 保留两位小数
     * 3. 最低支付金额1元
     */
    @Override
    public BigDecimal discountAmount(Double couponInfo, BigDecimal skuPrice) {
        BigDecimal discountAmount = skuPrice.multiply(new BigDecimal(couponInfo)).setScale(2, RoundingMode.HALF_UP);
        if (discountAmount.compareTo(BigDecimal.ZERO) < 1) {
            return BigDecimal.ONE;
        }
        return discountAmount;
    }

    @Override
    public boolean canApply(BigDecimal skuPrice) {
        return false;
    }
}
