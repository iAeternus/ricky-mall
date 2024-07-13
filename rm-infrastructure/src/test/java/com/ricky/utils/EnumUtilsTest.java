package com.ricky.utils;

import com.ricky.enums.impl.WeightUnit;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className EnumUtilsTest
 * @desc
 */
public class EnumUtilsTest {

    @Test
    public void fromCode() {
        // Given
        String g = "g";
        String kg = "kg";
        String oz = "oz";
        String lb = "lb";

        // When
        WeightUnit weightUnit1 = EnumUtils.fromCode(WeightUnit.class, g);
        // Then
        assertThat(weightUnit1).isEqualTo(WeightUnit.GRAM);

        // When
        WeightUnit weightUnit2 = EnumUtils.fromCode(WeightUnit.class, kg);
        // Then
        assertThat(weightUnit2).isEqualTo(WeightUnit.KILOGRAM);

        // When
        WeightUnit weightUnit3 = EnumUtils.fromCode(WeightUnit.class, oz);
        // Then
        assertThat(weightUnit3).isEqualTo(WeightUnit.OUNCE);

        // When
        WeightUnit weightUnit4 = EnumUtils.fromCode(WeightUnit.class, lb);
        // Then
        assertThat(weightUnit4).isEqualTo(WeightUnit.POUND);
    }

    @Test
    public void fromDescription() {
        // Given
        String g = "克";
        String kg = "千克";
        String oz = "盎司";
        String lb = "磅";

        // When
        WeightUnit weightUnit1 = EnumUtils.fromDescription(WeightUnit.class, g);
        // Then
        assertThat(weightUnit1).isEqualTo(WeightUnit.GRAM);

        // When
        WeightUnit weightUnit2 = EnumUtils.fromDescription(WeightUnit.class, kg);
        // Then
        assertThat(weightUnit2).isEqualTo(WeightUnit.KILOGRAM);

        // When
        WeightUnit weightUnit3 = EnumUtils.fromDescription(WeightUnit.class, oz);
        // Then
        assertThat(weightUnit3).isEqualTo(WeightUnit.OUNCE);

        // When
        WeightUnit weightUnit4 = EnumUtils.fromDescription(WeightUnit.class, lb);
        // Then
        assertThat(weightUnit4).isEqualTo(WeightUnit.POUND);
    }

}