package com.ricky.types;

import com.ricky.enums.impl.PasswordStrength;
import com.ricky.types.user.Password;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className PasswordTest
 * @desc
 */
public class PasswordTest {

    @Test
    public void veryWeak() {
        // Given
        Password password = new Password("abc", true);

        // Then
        assertThat(password.getStrength()).isEqualTo(PasswordStrength.VERY_WEAK);
    }

    @Test
    public void weak() {
        // Given
        Password password = new Password("123abc", true);

        // Then
        assertThat(password.getStrength()).isEqualTo(PasswordStrength.WEAK);
    }

    @Test
    public void medium() {
        // Given
        Password password = new Password("Password123", true);

        // Then
        assertThat(password.getStrength()).isEqualTo(PasswordStrength.MEDIUM);
    }

    @Test
    public void strong() {
        // Given
        Password password = new Password("SuperSecurePassword123!@#", true);

        // Then
        assertThat(password.getStrength()).isEqualTo(PasswordStrength.STRONG);
    }

}