package com.ricky.dto.command;

import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/2
 * @className ReduceStockCommand
 * @desc 扣减库存命令
 */
@Data
public class ReduceStockCommand implements Command {

    private Long commodityId;
    private Integer delta; // 扣减量

}
