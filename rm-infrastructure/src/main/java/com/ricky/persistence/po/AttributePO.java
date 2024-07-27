package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ricky.marker.Identifier;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className AttributePO
 * @desc 属性键值对PO实体
 */
@Data
@TableName("attributes")
@EqualsAndHashCode(callSuper = true)
public class AttributePO extends BasePO {

    @TableId
    private Long id;
    private Long commodityId; // 商品id
    private String attributeKey; // 商品属性键
    private String attributeValue; // 商品属性值

}
