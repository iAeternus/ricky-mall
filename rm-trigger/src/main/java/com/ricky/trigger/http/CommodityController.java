package com.ricky.trigger.http;

import com.ricky.dto.command.ModifyCommodityCommand;
import com.ricky.dto.command.ModifyCommodityPriceCommand;
import com.ricky.dto.command.ReduceStockCommand;
import com.ricky.dto.command.SaveCommodityCommand;
import com.ricky.dto.response.GetCommodityByIdResponse;
import com.ricky.model.Result;
import com.ricky.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/modify")
    @ApiOperation("修改商品信息")
    public Result<Void> modifyCommodity(@RequestBody ModifyCommodityCommand command) {
        commodityService.modifyCommodity(command);
        return Result.ok();
    }

    @GetMapping("/get/{id}")
    @ApiOperation("根据id查询商品")
    public Result<GetCommodityByIdResponse> getCommodityById(@PathVariable Long id) {
        GetCommodityByIdResponse response = commodityService.getCommodityById(id);
        return Result.ok(response);
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation("根据id删除商品信息")
    public Result<Void> removeCommodity(@PathVariable Long id) {
        commodityService.removeCommodity(id);
        return Result.ok();
    }

    @PutMapping("/reduce/stock")
    @ApiOperation("扣减库存")
    public Result<Void> reduceStock(@RequestBody ReduceStockCommand command) {
        commodityService.reduceStock(command);
        return Result.ok();
    }

    @PutMapping("/modify/price")
    @ApiOperation("变更商品价格")
    public Result<Void> modifyPrice(@RequestBody ModifyCommodityPriceCommand command) {
        commodityService.modifyPrice(command);
        return Result.ok();
    }

}
