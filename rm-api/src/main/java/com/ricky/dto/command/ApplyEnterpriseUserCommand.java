package com.ricky.dto.command;

import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className ApplyEnterpriseUserCommand
 * @desc 申请企业用户实体
 */
@Data
public class ApplyEnterpriseUserCommand implements Command {

    private Long userId;
    private String recordNumber; // 备案号
    private String name; // 公司名
    private String ceo; // 首席执行官

}
