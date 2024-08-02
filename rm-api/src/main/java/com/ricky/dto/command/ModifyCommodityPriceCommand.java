package com.ricky.dto.command;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/2
 * @className ModifyCommodityPriceCommand
 * @desc
 */
@Data
public class ModifyCommodityPriceCommand implements Command {

    private Long commodityId;
    private BigDecimal delta; // 变更值

}
