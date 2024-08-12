package com.ricky.domain.promotion.model.entity.impl;

import com.ricky.domain.promotion.model.entity.CouponPromotionStrategy;
import com.ricky.types.common.Money;

import java.math.BigDecimal;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className NYGPromotion
 * @desc n元购
 */
public class NYGPromotion implements CouponPromotionStrategy {

    /**
     * n元购信息，10 -> 10元购
     */
    private final Double couponInfo;

    public NYGPromotion(Double couponInfo) {
        this.couponInfo = couponInfo;
    }

    /**
     * n元购购买
     * 1. 无论原价多少钱都固定金额购买
     */
    @Override
    public Money discountAmount(Money skuPrice) {
        return new Money(couponInfo, skuPrice.getCurrency());
    }
}
