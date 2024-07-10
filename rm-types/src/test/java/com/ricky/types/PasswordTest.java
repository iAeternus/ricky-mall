package com.ricky.types;

import com.ricky.enums.PasswordStrength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className PasswordTest
 * @desc
 */
class PasswordTest {

    @Test
    @DisplayName("very weak")
    public void calculateStrengthWithVeryWeak() {
        // Given
        Password password = new Password("abc");

        // Then
        assertThat(password.getStrength()).isEqualTo(PasswordStrength.VERY_WEAK);
    }

    @Test
    @DisplayName("weak")
    public void calculateStrengthWithWeak() {
        // Given
        Password password = new Password("123abc");

        // Then
        assertThat(password.getStrength()).isEqualTo(PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("medium")
    public void calculateStrengthWithMedium() {
        // Given
        Password password = new Password("Password123");

        // Then
        assertThat(password.getStrength()).isEqualTo(PasswordStrength.MEDIUM);
    }

    @Test
    @DisplayName("strong")
    public void calculateStrengthWithStrong() {
        // Given
        Password password = new Password("SuperSecurePassword123!@#");

        // Then
        assertThat(password.getStrength()).isEqualTo(PasswordStrength.STRONG);
    }

}