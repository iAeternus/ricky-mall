package com.ricky.trigger.http;

import com.ricky.dto.command.AddIntegralCommand;
import com.ricky.dto.command.DepositCommand;
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

    @PutMapping("/integral/add")
    @ApiOperation("增加积分")
    public Result<Void> addIntegral(@RequestBody AddIntegralCommand command) {
        userService.addIntegral(command);
        return Result.ok();
    }

    @PutMapping("/deposit")
    @ApiOperation("存款")
    public Result<Void> deposit(@RequestBody DepositCommand command) {
        userService.deposit(command);
        return Result.ok();
    }

}
