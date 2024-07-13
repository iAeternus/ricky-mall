package com.ricky.dto.command;

import com.ricky.enums.impl.StoreType;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className ApplyForStoreAuthCommand
 * @desc 申请店铺认证命令实体
 */
@Data
public class ApplyForStoreAuthCommand implements Command {

    private Long userId;
    private String name; // 店铺名称
    private String boss; // 老板名称
    private String recordNumber; // 备案号
    private StoreType storeType; // 店铺类型

}
