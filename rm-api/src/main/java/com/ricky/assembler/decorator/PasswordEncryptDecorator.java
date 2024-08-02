package com.ricky.assembler.decorator;

import com.ricky.enums.impl.PasswordStatus;
import com.ricky.types.user.Password;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/2
 * @className PasswordEncryptDecorator
 * @desc
 */
@Service
public class PasswordEncryptDecorator {

    public Password map(String password) {
        return new Password(password, PasswordStatus.UNENCRYPTED);
    }

}
