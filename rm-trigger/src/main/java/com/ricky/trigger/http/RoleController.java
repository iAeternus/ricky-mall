package com.ricky.trigger.http;

import com.ricky.dto.command.ApplyEnterpriseUserCommand;
import com.ricky.dto.command.ApplyForStoreAuthCommand;
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
 * @className RoleController
 * @desc
 */
@RestController
@RequestMapping("/api/v1/role")
@Api(tags = "用户身份相关接口")
@RequiredArgsConstructor
public class RoleController {

    private final UserService userService;

    @PostMapping("/apply/enterprise")
    @ApiOperation("申请企业用户")
    public Result<Void> applyForEnterpriseUsers(@RequestBody ApplyEnterpriseUserCommand command) {
        userService.applyForEnterpriseUsers(command);
        return Result.ok();
    }

    @PostMapping("/apply/store")
    @ApiOperation("申请店铺认证")
    public Result<Void> applyForStoreAuth(@RequestBody ApplyForStoreAuthCommand command) {
        userService.applyForStoreAuth(command);
        return Result.ok();
    }

    // TODO 申请物流方、管理员

}
