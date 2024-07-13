package com.ricky.types.common;

import com.ricky.marker.ValueObject;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;

import static cn.hutool.core.lang.Assert.isTrue;
import static cn.hutool.core.lang.Assert.notNull;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className ExchangeRate
 * @desc 汇率
 */
@Value
public class ExchangeRate implements ValueObject {

    BigDecimal rate; // 汇率
    Currency from; // 原始货币
    Currency to; // 目标货币

    public ExchangeRate(BigDecimal rate, Currency from, Currency to) {
        this.rate = rate;
        this.from = from;
        this.to = to;
    }

    /**
     * 汇率金额转换
     */
    public Money exchangeTo(Money fromMoney) {
        notNull(fromMoney);
        isTrue(this.from.equals(fromMoney.getCurrency()));
        BigDecimal targetAmount = fromMoney.getAmount().multiply(rate);
        return new Money(targetAmount, to);
    }

    public static Money exchangeTo(BigDecimal rate, Money from, Currency to) {
        notNull(from);
        notNull(to);
        isTrue(from.getCurrency().equals(to));
        ExchangeRate exchangeRate = new ExchangeRate(rate, from.getCurrency(), to);
        return new Money(from.getAmount().multiply(rate));
    }

}