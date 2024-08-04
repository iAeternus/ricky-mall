package com.ricky.domain.promotion.model.entity;

import java.math.BigDecimal;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className CouponPromotionStrategy
 * @desc 优惠劵促销策略
 */
public interface CouponPromotionStrategy<T> {

    /**
     * 优惠券金额计算
     *
     * @param couponInfo 券折扣信息；直减、满减、折扣、N元购
     * @param skuPrice   sku金额
     * @return 优惠后金额
     */
    BigDecimal discountAmount(T couponInfo, BigDecimal skuPrice);

    boolean canApply(BigDecimal skuPrice);

}
