package com.ricky.service;

import com.ricky.dto.command.AddIntegralCommand;
import com.ricky.dto.command.ApplyEnterpriseUserCommand;
import com.ricky.dto.command.ApplyForStoreAuthCommand;
import com.ricky.dto.command.DepositCommand;
import com.ricky.dto.query.EmailQuery;
import com.ricky.dto.response.UserInfoResponse;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className UserService
 * @desc
 */
@Service
public interface UserService {
    void applyForEnterpriseUsers(ApplyEnterpriseUserCommand command);

    UserInfoResponse getByEmail(EmailQuery query);

    void addIntegral(AddIntegralCommand command);

    void deposit(DepositCommand command);

    void applyForStoreAuth(ApplyForStoreAuthCommand command);
}
