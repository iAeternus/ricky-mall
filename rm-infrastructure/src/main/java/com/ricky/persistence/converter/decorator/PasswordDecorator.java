package com.ricky.persistence.converter.decorator;

import com.ricky.enums.impl.PasswordStatus;
import com.ricky.types.user.Password;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className PasswordDecorator
 * @desc
 */
@Service
public class PasswordDecorator {

    public Password map(String password) {
        return new Password(password, PasswordStatus.ENCRYPTED);
    }

    public String map(Password password) {
        return password.getValue();
    }

}
