package com.ricky.types;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className IntegralTest
 * @desc
 */
public class IntegralTest {

    @Test
    public void getLevel() {
        // Given
        List<Integral> integrals = new ArrayList<>();
        for(long points = 10; points <= 1e8; points *= 10) {
            integrals.add(new Integral(points));
        }

        // When
        List<Integer> levels = integrals.stream()
                .map(Integral::getLevel)
                .toList();

        // Then
        assertThat(levels).isEqualTo(List.of(8, 15, 23, 30, 38, 45, 53, 60));
    }

}