package com.ricky.domain.promotion.model.entity;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className PromotionStrategyManager
 * @desc 促销策略管理器
 */
public class PromotionStrategyManager {

    // private static final Map<PromotionType, CouponPromotionStrategy<?>> STRATEGY_MAP = new HashMap<>();
    //
    // static {
    //     STRATEGY_MAP.put(PromotionType.MAX_OF, new PromotionStrategyHolder<>(new MaxOutPromotion()));
    //     STRATEGY_MAP.put(PromotionType.ZJ, new PromotionStrategyHolder<>(new ZJPromotion()));
    //     STRATEGY_MAP.put(PromotionType.DISCOUNT, new PromotionStrategyHolder<>(new DiscountPromotion()));
    //     STRATEGY_MAP.put(PromotionType.NYG, new PromotionStrategyHolder<>(new NYGPromotion()));
    // }
    //
    // public BigDecimal discountAmount(PromotionType type, Map<String, String> couponInfo, BigDecimal skuPrice) {
    //     return STRATEGY_MAP.get(type).discountAmount(couponInfo, skuPrice);
    // }
    //
    // private static class PromotionStrategyHolder<T> implements CouponPromotionStrategy<T> {
    //
    //     private final CouponPromotionStrategy<T> promotionStrategy;
    //
    //     public PromotionStrategyHolder(CouponPromotionStrategy<T> promotionStrategy) {
    //         this.promotionStrategy = promotionStrategy;
    //     }
    //
    //     @Override
    //     public BigDecimal discountAmount(T couponInfo, BigDecimal skuPrice) {
    //         return promotionStrategy.discountAmount(couponInfo, skuPrice);
    //     }
    // }

}
