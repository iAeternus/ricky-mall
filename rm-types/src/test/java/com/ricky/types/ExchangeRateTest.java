package com.ricky.types;

import com.ricky.types.common.ExchangeRate;
import com.ricky.types.common.Money;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className ExchangeRateTest
 * @desc
 */
public class ExchangeRateTest {

    @Test
    public void exchangeTo() {
        // Given
        Currency cny = Currency.getInstance("CNY"); // 人民币
        Currency usd = Currency.getInstance("USD"); // 美元
        ExchangeRate exchangeRate = new ExchangeRate(BigDecimal.valueOf(0.14), cny, usd);
        Money money = new Money(1000.5, cny);

        // When
        Money result = exchangeRate.exchangeTo(money);

        // Then
        assertThat(result.getAmount()).isEqualTo(BigDecimal.valueOf(140.07));
        assertThat(result.getCurrency()).isEqualTo(usd);
    }

}