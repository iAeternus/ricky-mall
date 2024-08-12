package com.ricky.domain.promotion.model.entity.impl;

import com.ricky.domain.promotion.model.entity.CouponPromotionStrategy;
import com.ricky.types.common.Money;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className MaxOutPromotion
 * @desc 满减
 */
public class MaxOutPromotion implements CouponPromotionStrategy {

    /**
     * 满减信息，满足x元后-n元
     * 键-x
     * 值-n
     */
    private final Map<String, String> couponInfo;

    public MaxOutPromotion(Map<String, String> couponInfo) {
        this.couponInfo = couponInfo;
    }

    /**
     * 满减计算
     * 1. 判断满足x元后-n元，否则不减
     * 2. 最低支付金额1元
     */
    @Override
    public Money discountAmount(Money skuPrice) {
        String x = couponInfo.get("x");
        String n = couponInfo.get("n");

        // 小于商品金额条件的，直接返回商品原价
        if (skuPrice.compareTo(new BigDecimal(x)) < 0) {
            return skuPrice;
        }

        // 减去优惠金额判断
        Money discountAmount = skuPrice.subtract(new BigDecimal(n));
        if (discountAmount.compareTo(BigDecimal.ZERO) < 1) {
            return new Money(BigDecimal.ONE, skuPrice.getCurrency());
        }

        return discountAmount;
    }
}
