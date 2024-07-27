package com.ricky.types.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ricky.exception.NullException;
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

    BigDecimal amount;

    // @Getter(AccessLevel.PRIVATE)
    Currency currency;

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_CURRENCY_CODE = "CNY";

    public static final Money ZERO = new Money(BigDecimal.ZERO, Currency.getInstance(DEFAULT_CURRENCY_CODE));

    @JsonCreator
    public Money(@JsonProperty("amount") BigDecimal amount, @JsonProperty("currency") Currency currency) {
        NullException.isNull(amount, "amount cannot be null");
        NullException.isNull(currency, "currency cannot be null");

        this.amount = amount;
        this.currency = currency;
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
        return amount.setScale(newScale, roundingMode);
    }

    public BigDecimal getAmount() {
        return getAmount(2, RoundingMode.HALF_UP);
    }

    public String currencyCode() {
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
    public Money add(Money money) {
        if (!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Currencies must be the same for addition");
        }
        return new Money(this.amount.add(money.amount), this.currency);
    }

    /**
     * 减法
     */
    public Money subtract(Money money) {
        if (!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Currencies must be the same for subtraction");
        }
        return new Money(this.amount.subtract(money.amount), this.currency);
    }

    /**
     * 乘法
     */
    public Money multiply(Money money) {
        if (!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Currencies must be the same for multiply");
        }
        return new Money(this.amount.multiply(money.amount).setScale(2, RoundingMode.HALF_UP), this.currency);
    }

    /**
     * 除法
     */
    public Money divide(Money money, int scale, RoundingMode roundingMode) {
        if (!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Currencies must be the same for division");
        }
        if (BigDecimal.ZERO.compareTo(money.amount) == 0) {
            throw new ArithmeticException("Divided by zero");
        }
        return new Money(this.amount.divide(money.amount, scale, roundingMode), this.currency);
    }

    public Money divide(Money money) {
        return divide(money, 2, RoundingMode.HALF_UP);
    }

    public int compareTo(Money money) {
        if (!this.currency.equals(money.currency)) {
            throw new IllegalArgumentException("Currencies must be the same for compare");
        }
        return this.amount.compareTo(money.amount);
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