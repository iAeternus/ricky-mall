package com.ricky.domain.promotion.model.entity;

import com.ricky.domain.promotion.model.entity.impl.DiscountPromotion;
import com.ricky.domain.promotion.model.entity.impl.MaxOutPromotion;
import com.ricky.domain.promotion.model.entity.impl.NYGPromotion;
import com.ricky.domain.promotion.model.entity.impl.ZJPromotion;
import com.ricky.enums.impl.PromotionType;
import com.ricky.types.common.Money;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/4
 * @className PromotionStrategyManager
 * @desc 促销策略管理器
 */
@Service
public class PromotionStrategyManager implements CouponPromotionStrategy {

    private final CouponPromotionStrategy strategy;

    public PromotionStrategyManager(PromotionType type) {
        this.strategy = selectImpl(type, selectCouponInfo(type));
    }

    private Object selectCouponInfo(PromotionType type) {
        return switch (type) {
            case MAX_OF -> Map.of("x", 100, "n", 12);
            case ZJ -> 10.0;
            case DISCOUNT -> 0.88;
            case NYG -> 1.0;
        };
    }

    @SuppressWarnings("unchecked")
    private CouponPromotionStrategy selectImpl(PromotionType type, Object couponInfo) {
        return switch (type) {
            case MAX_OF -> new MaxOutPromotion((Map<String, String>) couponInfo);
            case ZJ -> new ZJPromotion((Double) couponInfo);
            case DISCOUNT -> new DiscountPromotion((Double) couponInfo);
            case NYG -> new NYGPromotion((Double) couponInfo);
        };
    }

    @Override
    public Money discountAmount(Money skuPrice) {
        return strategy.discountAmount(skuPrice);
    }

}
