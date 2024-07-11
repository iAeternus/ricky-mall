package com.ricky.trigger.http;

import com.ricky.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className HelloController
 * @desc
 */
@RestController
@RequestMapping("/api/v1/hello")
@Api(tags = "测试接口")
@RequiredArgsConstructor
public class HelloController {

    @GetMapping
    @ApiOperation("测试接口")
    public Result<String> hello() {
        return Result.ok("Hello DDD!");
    }

}
