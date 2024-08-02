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

    public Currency map(String currencyCode) {
        return Currency.getInstance(currencyCode);
    }

    public String map(Currency currency) {
        return currency.getCurrencyCode();
    }

}
