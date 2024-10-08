package com.ricky.types.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricky.marker.ValueObject;
import lombok.Builder;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * @author Ricky
 * @version 2.0 新增了若干构造函数
 * @date 2024/7/27
 * @className Money
 * @desc 金额
 */
@Value
@Builder
public class Money implements ValueObject, Serializable {

    /**
     * 金额数额
     */
    BigDecimal amount;

    /**
     * 货币类型
     */
    Currency currency;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 默认货币类型
     */
    public static final String DEFAULT_CURRENCY_CODE = "CNY";

    /**
     * 默认保留小数位数
     */
    public static final int DEFAULT_SCALE = 2;

    /**
     * 默认舍入方式
     */
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    /**
     * 0元
     */
    public static final Money ZERO = new Money(BigDecimal.ZERO, Currency.getInstance(DEFAULT_CURRENCY_CODE));

    public Money(BigDecimal amount, Currency currency) {
        if (amount != null && BigDecimal.ZERO.compareTo(amount) > 0) {
            throw new IllegalArgumentException("金额不能为负数，amount=" + amount + " currency=" + currency);
        }

        this.amount = amount != null ? amount.setScale(2, RoundingMode.HALF_UP) : null;
        this.currency = amount != null ? currency : null;
    }

    @JsonCreator
    public Money(@JsonProperty("amount") BigDecimal amount, @JsonProperty("currency") String currencyCode) {
        this(amount, Currency.getInstance(currencyCode));
    }

    public Money(double amount, Currency currency) {
        this(BigDecimal.valueOf(amount), currency);
    }

    public Money(double amount, String currencyCode) {
        this(BigDecimal.valueOf(amount), Currency.getInstance(currencyCode));
    }

    public Money(long amount, Currency currency) {
        this(BigDecimal.valueOf(amount), currency);
    }

    public Money(long amount, String currencyCode) {
        this(BigDecimal.valueOf(amount), Currency.getInstance(currencyCode));
    }

    public Money(BigDecimal amount) {
        this(amount, Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    public Money(double amount) {
        this(BigDecimal.valueOf(amount), Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    public Money(long amount) {
        this(BigDecimal.valueOf(amount), Currency.getInstance(DEFAULT_CURRENCY_CODE));
    }

    public BigDecimal getAmount(int newScale, RoundingMode roundingMode) {
        if (amount == null) {
            return null;
        }
        return amount.setScale(newScale, roundingMode);
    }

    public BigDecimal getAmount() {
        return getAmount(2, RoundingMode.HALF_UP);
    }

    public String currencyCode() {
        if (currency == null) {
            return null;
        }
        return currency.getCurrencyCode();
    }

    public static boolean isSameCurrency(Money m1, Money m2) {
        return m1.currency.equals(m2.currency);
    }

    public static boolean isSameCurrency(Money money, Currency currency) {
        return currency.equals(money.currency);
    }

    /**
     * 加法
     */
    public Money add(BigDecimal amount, int scale, RoundingMode roundingMode) {
        return new Money(this.amount.add(amount).setScale(scale, roundingMode), this.currency);
    }

    public Money add(BigDecimal amount) {
        return add(amount, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public Money add(Money money, int scale, RoundingMode roundingMode) {
        if (!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Currencies must be the same for addition");
        }
        return add(money.amount, scale, roundingMode);
    }

    public Money add(Money money) {
        return add(money, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 减法
     */
    public Money subtract(BigDecimal amount, int scale, RoundingMode roundingMode) {
        return new Money(this.amount.subtract(amount).setScale(scale, roundingMode), this.currency);
    }

    public Money subtract(BigDecimal amount) {
        return subtract(amount, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public Money subtract(Money money, int scale, RoundingMode roundingMode) {
        if (!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Currencies must be the same for subtraction");
        }
        return subtract(money.amount, scale, roundingMode);
    }

    public Money subtract(Money money) {
        return subtract(money, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 乘法
     */
    public Money multiply(BigDecimal amount, int scale, RoundingMode roundingMode) {
        return new Money(this.amount.multiply(amount).setScale(scale, roundingMode), this.currency);
    }

    public Money multiply(BigDecimal amount) {
        return multiply(amount, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public Money multiply(Money money, int scale, RoundingMode roundingMode) {
        if (!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Currencies must be the same for multiply");
        }
        return multiply(money.amount, scale, roundingMode);
    }

    public Money multiply(Money money) {
        return multiply(money, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    /**
     * 除法
     */
    public Money divide(BigDecimal amount, int scale, RoundingMode roundingMode) {
        if (BigDecimal.ZERO.compareTo(amount) == 0) {
            throw new ArithmeticException("Divided by zero");
        }
        return new Money(this.amount.divide(amount, scale, roundingMode), this.currency);
    }

    public Money divide(BigDecimal amount) {
        return divide(amount, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public Money divide(Money money, int scale, RoundingMode roundingMode) {
        if (!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Currencies must be the same for division");
        }
        return divide(money.amount, scale, roundingMode);
    }

    public Money divide(Money money) {
        return divide(money, DEFAULT_SCALE, DEFAULT_ROUNDING_MODE);
    }

    public int compareTo(BigDecimal amount) {
        return this.amount.compareTo(amount);
    }

    public int compareTo(Money money) {
        if (!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Currencies must be the same for compare");
        }
        return compareTo(money.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    public String format() {
        return currency.getSymbol() + " " + amount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }

}