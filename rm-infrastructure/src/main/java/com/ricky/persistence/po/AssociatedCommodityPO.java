package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className AssociatedCommodityPO
 * @desc 关联商品
 */
@Data
@TableName("associated_commodity")
@EqualsAndHashCode(callSuper = true)
public class AssociatedCommodityPO extends BasePO {

    @TableId
    private Long id;
    private Long commodityId; // 商品id
    private Long relatedCommodityId; // 相关商品ID
    private Long skuIds; // 对应的SKU ID

}
