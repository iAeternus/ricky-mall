package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ricky.enums.impl.CommodityType;
import com.ricky.enums.impl.WeightUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityPO
 * @desc
 */
@Data
@Builder
@AllArgsConstructor
@TableName("commodity")
@EqualsAndHashCode(callSuper = true)
public class CommodityPO extends BasePO {

    @TableId
    private Long id;
    private String name; // 商品名称
    private String description; // 商品描述
    private BigDecimal price; // 商品价格
    private String currencyCode; // 货币类型
    private Integer stock; // 商品库存量
    private CommodityType commodityType; // 商品状态
    private String mainImageUrl; // 主图URL

    // 分类与属性
    private Long categoryId; // 分类id
    private String brand; // 品牌

    // 促销信息
    private BigDecimal discountPrice; // 折扣价
    private LocalDateTime promotionStartTime; // 促销开始时间
    private LocalDateTime promotionEndTime; // 促销结束时间

    // 销售信息
    private Integer soldCount; // 销售数量

    // 发货信息
    private Double weight; // 商品重量值
    private WeightUnit weightUnit; // 商品重量单位

    // 供应商信息
    private Long supplierId; // 供应商ID

    // SEO信息
    private String metaTitle; // SEO标题
    private String metaKeywords; // SEO关键词
    private String metaDescription; // SEO描述

}
