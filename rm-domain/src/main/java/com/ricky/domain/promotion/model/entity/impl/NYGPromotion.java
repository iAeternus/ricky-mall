package com.ricky.domain.promotion.model.entity.impl;

import com.ricky.domain.promotion.model.entity.CouponPromotionStrategy;

import java.math.BigDecimal;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className NYGPromotion
 * @desc n元购
 */
public class NYGPromotion implements CouponPromotionStrategy<Double> {

    /**
     * n元购购买
     * 1. 无论原价多少钱都固定金额购买
     */
    @Override
    public BigDecimal discountAmount(Double couponInfo, BigDecimal skuPrice) {
        return new BigDecimal(couponInfo);
    }

    @Override
    public boolean canApply(BigDecimal skuPrice) {
        return false;
    }
}
