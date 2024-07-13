package com.ricky.types.common;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className MoneyTest
 * @desc
 */
public class MoneyTest {

    @Test
    public void add() {
        // Given
        Money m1 = new Money(1000.5);
        Money m2 = new Money(1000.5);

        // When
        Money result = m1.add(m2);

        // Then
        assertThat(result.format()).isEqualTo("¥ 2001.00");
    }

    @Test
    public void compareTo() {
        // Given
        Money m1 = new Money(999.99);
        Money m2 = new Money(1000.00);
        Money m3 = new Money(1000.00);

        // When
        int result1 = m1.compareTo(m2);
        int result2 = m2.compareTo(m1);
        int result3 = m2.compareTo(m3);

        // Then
        assertThat(result1).isEqualTo(-1);
        assertThat(result2).isEqualTo(1);
        assertThat(result3).isEqualTo(0);
    }

}