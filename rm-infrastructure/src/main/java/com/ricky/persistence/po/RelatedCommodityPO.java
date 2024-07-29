package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className RelatedCommodityPO
 * @desc 关联商品
 */
@Data
@Builder
@TableName("related_commodity")
@EqualsAndHashCode(callSuper = true)
public class RelatedCommodityPO extends BasePO {

    @TableId
    private Long id;
    private Long commodityId; // 商品id
    private Long relatedCommodityId; // 相关商品ID

}
