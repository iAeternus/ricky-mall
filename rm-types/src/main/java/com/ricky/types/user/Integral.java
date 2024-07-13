package com.ricky.types.user;

import com.ricky.exception.ForbiddenException;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className Integral
 * @desc 积分
 */
@Value
public class Integral implements ValueObject {

    Long value;

    public static final Integral ZERO = new Integral(0L);

    public Integral(Long value) {
        if (value == null) {
            throw new IllegalArgumentException("积分不能为空");
        }
        if (value < 0) {
            throw new ForbiddenException("积分不能为负");
        }
        this.value = value;
    }

    private static final double BASE = Math.E; // 可以选择其他值，但自然对数的底数是一个常用的选择
    private static final double OFFSET = 1.0; // 防止对数函数中的负值
    private static final double MAX_POINTS = 1e8; // 积分达到这个值时，等级应为60
    // 达到60级所需的原始等级值
    private static final double MAX_RAW_LEVEL = Math.log(MAX_POINTS + OFFSET) / Math.log(BASE);
    // 缩放因子
    private static final double SCALE_FACTOR = 60.0 / MAX_RAW_LEVEL;

    /**
     * 根据积分计算等级
     *
     * @return 用户的等级，四舍五入到最接近的整数
     */
    public int getLevel() {
        // 计算未经缩放的等级（现在GROWTH_FACTOR为1，所以直接计算对数）
        double rawLevel = Math.log(value + OFFSET) / Math.log(BASE);

        // 应用缩放因子，并四舍五入到最接近的整数
        return (int) Math.round(rawLevel * SCALE_FACTOR);
    }

}
