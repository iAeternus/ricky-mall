package com.ricky.trigger.http;

import com.ricky.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    @ApiOperation("测试接口")
    public Result<String> hello() {
        return Result.ok("Hello DDD!");
    }

}
