package com.ricky.types.commodity;

import com.ricky.enums.impl.WeightUnit;
import junit.framework.TestCase;
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

}