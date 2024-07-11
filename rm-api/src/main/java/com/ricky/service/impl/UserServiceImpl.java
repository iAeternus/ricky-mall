package com.ricky.service.impl;

import com.ricky.assembler.UserAssembler;
import com.ricky.domain.user.model.aggregate.User;
import com.ricky.domain.user.model.entity.EnterpriseUser;
import com.ricky.domain.user.service.UserDomainService;
import com.ricky.dto.command.ApplyEnterpriseUserCommand;
import com.ricky.dto.query.EmailQuery;
import com.ricky.dto.response.UserInfoResponse;
import com.ricky.enums.UserRole;
import com.ricky.service.UserService;
import com.ricky.types.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className UserServiceImpl
 * @desc
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserAssembler userAssembler;
    private final UserDomainService userDomainService;

    @Override
    public void applyForEnterpriseUsers(ApplyEnterpriseUserCommand command) {
        User user = userDomainService.getById(command.getUserId());
        userDomainService.changeRole(user, UserRole.ENTERPRISE_USERS);
        EnterpriseUser enterpriseUser = userAssembler.toEnterpriseUser(command);
        userDomainService.saveEnterpriseUser(enterpriseUser);
    }

    @Override
    public UserInfoResponse getByEmail(EmailQuery query) {
        Email email = userAssembler.emailFactory(query);
        User user = userDomainService.getByEmail(email);
        return userAssembler.userInfoResponseFactory(user);
    }

}
