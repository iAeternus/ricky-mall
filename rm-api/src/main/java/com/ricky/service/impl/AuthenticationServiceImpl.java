package com.ricky.service.impl;

import com.ricky.assembler.UserAssembler;
import com.ricky.domain.user.model.aggregate.User;
import com.ricky.domain.user.service.UserDomainService;
import com.ricky.dto.command.RegisterCommand;
import com.ricky.dto.query.AuthenticationQuery;
import com.ricky.dto.response.AuthenticationResponse;
import com.ricky.dto.response.RegisterResponse;
import com.ricky.properties.JwtProperties;
import com.ricky.service.AuthenticationService;
import com.ricky.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


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
    private final JwtProperties jwtProperties;

    @Override
    @Transactional
    public RegisterResponse register(RegisterCommand request) {
        User user = userAssembler.convert(request);
        userDomainService.saveUser(user);
        Map<String, Object> claims = userDomainService.getClaims(user);
        String jwt = JwtUtils.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        return userAssembler.convert(jwt, user.getPassword().getStrength());
    }

    @Override
    public AuthenticationResponse authentication(AuthenticationQuery request) {
        User user = userAssembler.convert(request);
        user = userDomainService.login(user);
        Map<String, Object> claims = userDomainService.getClaims(user);
        String jwt = JwtUtils.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claims);
        return userAssembler.convert(jwt);
    }

}
