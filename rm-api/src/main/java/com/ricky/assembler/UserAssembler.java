package com.ricky.assembler;

import com.ricky.domain.user.model.aggregate.User;
import com.ricky.domain.user.model.entity.EnterpriseUser;
import com.ricky.dto.command.ApplyEnterpriseUserCommand;
import com.ricky.dto.command.RegisterCommand;
import com.ricky.dto.query.AuthenticationQuery;
import com.ricky.dto.response.AuthenticationResponse;
import com.ricky.dto.response.RegisterResponse;
import com.ricky.enums.PasswordStrength;
import com.ricky.types.*;
import org.apache.ibatis.javassist.scopedpool.ScopedClassPoolFactoryImpl;
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
        user.setPassword(new Password(request.getPassword(), true));
        user.setNickname(new Nickname(request.getNickname()));
        user.setRealName(new RealName(request.getFirstName(), request.getLastName()));
        user.setPhoneNumber(new PhoneNumber(request.getPhoneNumber()));
        return user;
    }

    public User toUser(AuthenticationQuery request) {
        User user = new User();
        user.setEmail(new Email(request.getEmail()));
        user.setPassword(new Password(request.getPassword(), true));
        return user;
    }

    public RegisterResponse registerResponseFactory(String token, PasswordStrength strength) {
        return new RegisterResponse(token, strength);
    }

    public AuthenticationResponse authenticationResponseFactory(String token) {
        return new AuthenticationResponse(token);
    }

    public EnterpriseUser toEnterpriseUser(ApplyEnterpriseUserCommand command) {
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        enterpriseUser.setUserId(new UserId(command.getUserId()));
        enterpriseUser.setCompany(new Company(command.getRecordNumber(), command.getName(), command.getCeo()));
        return enterpriseUser;
    }
}
