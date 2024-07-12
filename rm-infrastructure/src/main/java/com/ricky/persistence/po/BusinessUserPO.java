package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ricky.enums.StoreType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className BusinessUserPO
 * @desc
 */
@Data
@TableName("business_user")
@EqualsAndHashCode(callSuper = true)
public class BusinessUserPO extends BasePO {

    @TableId
    private Long id;
    private Long userId;
    private String storeName; // 店铺名称
    private String boss; // 老板名称
    private String recordNumber; // 备案号
    private StoreType storeType; // 店铺类型

}
