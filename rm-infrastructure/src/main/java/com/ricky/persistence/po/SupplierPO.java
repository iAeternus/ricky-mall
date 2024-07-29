package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/28
 * @className SupplierPO
 * @desc
 */
@Data
@Builder
@TableName("supplier")
@EqualsAndHashCode(callSuper = true)
public class SupplierPO extends BasePO {

    @TableId
    private Long id;
    private Long commodityId; // 商品id
    private String name; // 供应商名称
    private String contact; // 联系方式
    private String address; // 地址

}
