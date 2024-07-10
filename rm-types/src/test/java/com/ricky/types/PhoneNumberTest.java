package com.ricky.types;


import org.junit.Test;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className PhoneNumberTest
 * @desc
 */
public class PhoneNumberTest {

    @Test
    public void testPhoneNumber() {
        // Given
        PhoneNumber phoneNumber = new PhoneNumber("13872069156");

        // Then
        System.out.println(phoneNumber);
    }

}