package com.ricky.assembler;

import com.ricky.domain.user.model.User;
import com.ricky.dto.command.RegisterCommand;
import com.ricky.types.*;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className UserAssembler
 * @desc
 */
@Service
public class UserAssembler {

    public User toUser(RegisterCommand request) {
        User user = new User();
        user.setEmail(new Email(request.getEmail()));
        user.setPassword(new Password(request.getPassword()));
        user.setNickname(new Nickname(request.getNickname()));
        user.setRealName(new RealName(request.getFirstName(), request.getLastName()));
        user.setPhoneNumber(new PhoneNumber(request.getPhoneNumber()));
        return user;
    }
}
