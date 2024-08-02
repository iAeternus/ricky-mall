package com.ricky.api.assembler;

import com.ricky.assembler.UserAssembler;
import com.ricky.domain.user.model.aggregate.User;
import com.ricky.dto.command.RegisterCommand;
import com.ricky.dto.query.AuthenticationQuery;
import com.ricky.dto.query.EmailQuery;
import com.ricky.dto.response.AuthenticationResponse;
import com.ricky.dto.response.RegisterResponse;
import com.ricky.dto.response.UserInfoResponse;
import com.ricky.enums.impl.PasswordStatus;
import com.ricky.enums.impl.PasswordStrength;
import com.ricky.enums.impl.UserRole;
import com.ricky.types.common.Money;
import com.ricky.types.user.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/2
 * @className UserAssemblerTest
 * @desc
 */
@SpringBootTest
public class UserAssemblerTest {

    @Autowired
    private UserAssembler userAssembler;

    @Test
    public void convert1() {
        // Given
        RegisterCommand command = RegisterCommand.builder()
                .email("10449469060@qq.com")
                .password("123456")
                .nickname("Ricky")
                .firstName("Wu")
                .lastName("Zi wei")
                .phoneNumber("13872069156")
                .build();

        // When
        User user = userAssembler.convert(command);

        // Then
        assertThat(user).isEqualTo(User.builder()
                .email(new Email("10449469060@qq.com"))
                .password(new Password("123456", PasswordStatus.NO_ENCRYPTION_REQUIRED))
                .nickname(new Nickname("Ricky"))
                .realName(new RealName("Wu", "Zi wei"))
                .phoneNumber(new PhoneNumber("13872069156"))
                .build());
    }

    @Test
    public void convert2() {
        // Given
        AuthenticationQuery query = new AuthenticationQuery("10449469060@qq.com", "123456");

        // When
        User user = userAssembler.convert(query);

        // Then
        assertThat(user).isEqualTo(User.builder()
                .email(new Email("10449469060@qq.com"))
                .password(new Password("123456", PasswordStatus.NO_ENCRYPTION_REQUIRED))
                .build());
    }

    @Test
    public void convert3() {
        // Given
        String token = "xxx";
        PasswordStrength strength = PasswordStrength.STRONG;

        // When
        RegisterResponse response = userAssembler.convert(token, strength);

        // Then
        assertThat(response).isEqualTo(new RegisterResponse("xxx", PasswordStrength.STRONG));
    }

    @Test
    public void convert4() {
        // Given
        String token = "xxx";

        // When
        AuthenticationResponse response = userAssembler.convert(token);

        // Then
        assertThat(response).isEqualTo(new AuthenticationResponse("xxx"));
    }

    @Test
    public void convert5() {
        // Given
        EmailQuery query = new EmailQuery("10449469060@qq.com");

        // When
        Email email = userAssembler.convert(query);

        // Then
        assertThat(email).isEqualTo(new Email("10449469060@qq.com"));
    }

    @Test
    public void convert6() {
        // Given
        User user = User.builder()
                .id(new UserId(1L))
                .email(new Email("10449469060@qq.com"))
                .password(new Password("123456", PasswordStatus.NO_ENCRYPTION_REQUIRED))
                .nickname(new Nickname("Ricky"))
                .realName(new RealName("Wu", "Zi wei"))
                .phoneNumber(new PhoneNumber("13872069156"))
                .role(UserRole.ORDINARY_USERS)
                .integral(Integral.ZERO)
                .level(Level.ZERO)
                .balance(new Money(0))
                .build();

        // When
        UserInfoResponse response = userAssembler.convert(user);

        // Then
        assertThat(response).isEqualTo(UserInfoResponse.builder()
                .id(1L)
                .email("10449469060@qq.com")
                .nickname("Ricky")
                .firstName("Wu")
                .lastName("Zi wei")
                .phoneNumber("13872069156")
                .role(UserRole.ORDINARY_USERS)
                .integral(Integral.ZERO.getValue())
                .level(Level.ZERO.getValue())
                .balance(new Money(0))
                .build());
    }

}
