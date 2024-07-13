package com.ricky.service.impl;

import cn.hutool.core.math.Money;
import com.ricky.assembler.UserAssembler;
import com.ricky.domain.user.model.aggregate.User;
import com.ricky.domain.user.model.entity.BusinessUser;
import com.ricky.domain.user.model.entity.EnterpriseUser;
import com.ricky.domain.user.service.UserDomainService;
import com.ricky.dto.command.AddIntegralCommand;
import com.ricky.dto.command.ApplyEnterpriseUserCommand;
import com.ricky.dto.command.ApplyForStoreAuthCommand;
import com.ricky.dto.command.DepositCommand;
import com.ricky.dto.query.EmailQuery;
import com.ricky.dto.response.UserInfoResponse;
import com.ricky.enums.impl.UserRole;
import com.ricky.service.UserService;
import com.ricky.types.user.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Currency;

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

    @Override
    public void addIntegral(AddIntegralCommand command) {
        User user = userDomainService.getById(command.getUserId());
        user.addIntegral(command.getIntegral());
        userDomainService.updateUserById(user);
    }

    @Override
    public void deposit(DepositCommand command) {
        User user = userDomainService.getById(command.getUserId());
        user.increaseBalance(new Money(command.getAmount(),
                Currency.getInstance(command.getCurrencyCode())));
    }

    @Override
    public void applyForStoreAuth(ApplyForStoreAuthCommand command) {
        User user = userDomainService.getById(command.getUserId());
        userDomainService.changeRole(user, UserRole.BUSINESS);
        BusinessUser businessUser = userAssembler.toBusinessUser(command);
        userDomainService.saveBusinessUser(businessUser);
    }

}
