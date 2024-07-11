package com.ricky.service;

import com.ricky.dto.command.RegisterCommand;
import com.ricky.dto.query.AuthenticationQuery;
import com.ricky.dto.response.AuthenticationResponse;
import com.ricky.dto.response.RegisterResponse;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className AuthenticationService
 * @desc
 */
public interface AuthenticationService {
    RegisterResponse register(RegisterCommand request);

    AuthenticationResponse authentication(AuthenticationQuery request);
}
