package com.ricky.trigger.http;

import com.ricky.dto.query.AuthenticationQuery;
import com.ricky.dto.command.RegisterCommand;
import com.ricky.dto.response.AuthenticationResponse;
import com.ricky.dto.response.RegisterResponse;
import com.ricky.model.Result;
import com.ricky.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className AuthenticationController
 * @desc 认证登录相关接口
 */
@RestController
@RequestMapping("/api/v1/auth")
@Api(tags = "认证相关接口")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<RegisterResponse> register(@RequestBody RegisterCommand request) {
        return Result.ok(authenticationService.register(request));
    }

    @ApiOperation("认证")
    @PostMapping("/authenticate")
    public Result<AuthenticationResponse> authenticate(@RequestBody AuthenticationQuery request) {
        return Result.ok(authenticationService.authentication(request));
    }

}
