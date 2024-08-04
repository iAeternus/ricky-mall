package com.ricky.types.commodity;

import com.ricky.enums.BaseEnum;
import com.ricky.enums.impl.WeightUnit;
import com.ricky.types.common.Weight;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className WeightTest
 * @desc
 */
public class WeightTest {

    @Test
    public void convert() {
        // Given
        Weight weight = new Weight(1000.0, WeightUnit.GRAM);

        // When
        Weight result = weight.convert(WeightUnit.KILOGRAM);

        // Then
        assertThat(result.toString()).isEqualTo("1.0千克");
    }

    @Test
    public void test() {
        System.out.println(WeightUnit.KILOGRAM.getCode());
        System.out.println(WeightUnit.KILOGRAM.getMsg());

        String kg = BaseEnum.of(WeightUnit.class, "kg").getMsg();
        System.out.println(kg);
    }

}