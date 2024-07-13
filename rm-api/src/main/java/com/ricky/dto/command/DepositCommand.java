package com.ricky.dto.command;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className DepositCommand
 * @desc
 */
@Data
public class DepositCommand implements Command {

    private Long userId;
    private BigDecimal amount;
    private String currencyCode;

}
