package com.ricky.dto.command;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className AddIntegralCommand
 * @desc
 */
@Data
public class AddIntegralCommand implements Command {

    private Long userId; // 用户id
    private Long integral; // 增加值

}
