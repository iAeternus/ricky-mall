package com.ricky.types;


import com.ricky.types.user.Email;
import org.junit.Test;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className EmailTest
 * @desc
 */
public class EmailTest {

    @Test
    public void testEmail() {
        // Given
        Email email = new Email("10449469060@qq.com");

        // Then
        System.out.println(email);
    }

}