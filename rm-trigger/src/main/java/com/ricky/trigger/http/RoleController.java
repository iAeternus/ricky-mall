package com.ricky.trigger.http;

import com.ricky.dto.command.ApplyEnterpriseUserCommand;
import com.ricky.model.Result;
import com.ricky.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/enterprise")
    @ApiOperation("申请企业用户")
    public Result<Void> applyForEnterpriseUsers(@RequestBody ApplyEnterpriseUserCommand command) {
        userService.applyForEnterpriseUsers(command);
        return Result.ok();
    }

}