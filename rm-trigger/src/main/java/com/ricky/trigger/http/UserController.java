package com.ricky.trigger.http;

import com.ricky.dto.query.EmailQuery;
import com.ricky.dto.response.UserInfoResponse;
import com.ricky.model.Result;
import com.ricky.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className UserController
 * @desc 用户相关接口
 */
@RestController
@RequestMapping("/api/v1/user")
@Api(tags = "用户相关接口")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/email")
    @ApiOperation("根据邮箱查询用户")
    public Result<UserInfoResponse> getByEmail(EmailQuery query) {
        return Result.ok(userService.getByEmail(query));
    }

}
