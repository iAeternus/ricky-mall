package com.ricky.types.commodity;

import com.ricky.enums.impl.WeightUnit;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className Weight
 * @desc 重量
 */
@Value
public class Weight implements ValueObject {

    Double value; // 重量值
    WeightUnit unit; // 重量单位

    public Weight(Double value, WeightUnit unit) {
        if(value == null) {
            throw new IllegalArgumentException("重量值不能为空");
        }
        if(unit == null) {
            throw new IllegalArgumentException("重量单位不能为空");
        }
        this.value = value;
        this.unit = unit;
    }

    /**
     * 重量单位转换
     * @param fromUnit 起始单位
     * @param toUnit 目标单位
     * @param value 重量值，单位为起始单位
     * @return 返回目标单位的重量值
     */
    public static Weight convert(WeightUnit fromUnit, WeightUnit toUnit, double value) {
        return new Weight(toUnit.fromGrams(fromUnit.toGrams(value)), toUnit);
    }

    /**
     * 重量单位转换
     * @param targetUnit 目标单位
     * @return 返回目标单位的重量值
     */
    public Weight convert(WeightUnit targetUnit) {
        return new Weight(targetUnit.fromGrams(this.unit.toGrams(this.value)), targetUnit);
    }

    @Override
    public String toString() {
        return value + unit.getDescription();
    }

}
