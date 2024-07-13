package com.ricky.domain.user.model.aggregate;

import cn.hutool.core.math.Money;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import com.ricky.types.user.Level;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className UserTest
 * @desc
 */
public class UserTest {

    @Test
    public void addIntegral() {
        // Given
        User user = new User();

        // When
        user.addIntegral(114514L);

        // Then
        assertThat(user.getLevel()).isEqualTo(new Level(38));
    }

    @Test
    public void increaseBalance() {
        // Given
        User user = new User();
        Money money = new Money(1000.5);

        // When
        user.increaseBalance(money);
        user.increaseBalance(money);

        // Then
        assertThat(user.getBalance()).isEqualTo(new Money(2001));
    }

    @Test
    public void deductionBalance() {
        // Given
        User user = new User();
        user.increaseBalance(new Money(2001));
        Money money = new Money(1000.5);

        // When
        user.deductionBalance(money);

        // Then
        assertThat(user.getBalance()).isEqualTo(new Money(1000.5));
    }

    @Test
    public void test() {
        Money money = new Money(100000);
        JSON json = new JSONObject(money);
        System.out.println(json);
    }

}