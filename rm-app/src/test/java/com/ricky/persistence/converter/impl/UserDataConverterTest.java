package com.ricky.persistence.converter.impl;

import com.ricky.domain.user.model.aggregate.User;
import com.ricky.enums.impl.PasswordStatus;
import com.ricky.enums.impl.UserRole;
import com.ricky.persistence.po.UserPO;
import com.ricky.types.common.Money;
import com.ricky.types.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className UserDataConverterTest
 * @desc
 */
@SpringBootTest
public class UserDataConverterTest {

    @Autowired
    private UserDataConverter userDataConverter;

    @Test
    public void convert() {
        // Given
        Integral integral = new Integral(114514L);
        User user = new User(
                new UserId(1L),
                new Email("1049469060@qq.com"),
                new Password("123456", PasswordStatus.ENCRYPTED),
                new Nickname("Ricky"),
                new RealName("Wu", "Zi wei"),
                new PhoneNumber("13872069156"),
                UserRole.ORDINARY_USERS,
                integral,
                new Level(integral.getLevel()),
                new Money(10000)
        );

        // When
        UserPO userPO = userDataConverter.convert(user);
        User user1 = userDataConverter.convert(userPO);

        // Then
        assertThat(user).isEqualTo(user1);
    }

}
