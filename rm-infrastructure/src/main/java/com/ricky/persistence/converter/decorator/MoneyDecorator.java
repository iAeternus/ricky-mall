package com.ricky.persistence.converter.decorator;

import org.springframework.stereotype.Service;

import java.util.Currency;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className MoneyDecorator
 * @desc 货币类型装饰器
 */
@Service
public class MoneyDecorator {

    public Currency convert(String currencyCode) {
        return Currency.getInstance(currencyCode);
    }

    public String convert(Currency currency) {
        return currency.getCurrencyCode();
    }

}
