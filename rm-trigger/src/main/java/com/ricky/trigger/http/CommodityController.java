package com.ricky.trigger.http;

import com.ricky.dto.command.SaveCommodityCommand;
import com.ricky.model.Result;
import com.ricky.service.CommodityService;
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
 * @date 2024/7/13
 * @className CommodityController
 * @desc 商品相关接口
 */
@RestController
@RequestMapping("/api/v1/commodity")
@Api(tags = "商品相关接口")
@RequiredArgsConstructor
public class CommodityController {

    private final CommodityService commodityService;

    @PostMapping("/save")
    @ApiOperation("新增商品")
    public Result<Void> saveCommodity(@RequestBody SaveCommodityCommand command) {
        commodityService.saveCommodity(command);
        return Result.ok();
    }

}
