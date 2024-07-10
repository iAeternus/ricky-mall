package com.ricky.service.impl;

import com.ricky.assembler.UserAssembler;
import com.ricky.domain.user.model.User;
import com.ricky.domain.user.service.UserDomainService;
import com.ricky.dto.command.RegisterCommand;
import com.ricky.dto.query.AuthenticationQuery;
import com.ricky.dto.response.AuthenticationResponse;
import com.ricky.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className AuthenticationServiceImpl
 * @desc 认证服务
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserAssembler userAssembler;
    private final UserDomainService userDomainService;

    @Override
    public AuthenticationResponse register(RegisterCommand request) {
        User user = userAssembler.toUser(request);
        userDomainService.saveUser(user);
        return null;
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationQuery request) {
        return null;
    }

}
