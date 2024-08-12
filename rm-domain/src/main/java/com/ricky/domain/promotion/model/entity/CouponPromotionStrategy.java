package com.ricky.domain.promotion.model.entity;

import com.ricky.types.common.Money;

import java.math.BigDecimal;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className CouponPromotionStrategy
 * @desc 优惠劵促销策略
 */
public interface CouponPromotionStrategy {

    /**
     * 优惠券金额计算
     *
     * @param skuPrice   sku金额
     * @return 优惠后金额
     */
    Money discountAmount(Money skuPrice);

}
