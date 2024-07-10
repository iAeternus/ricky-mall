package com.ricky.types;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className RealNameTest
 * @desc
 */
class RealNameTest {

    @Test
    void testToString() {
        // Given
        RealName realName = new RealName("吴", "子维");

        // When
        String result = realName.toString();

        // Then
        assertThat(result).isEqualTo("吴 子维");
    }

}
