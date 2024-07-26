package com.ricky.dto.command;

import com.ricky.enums.impl.CommodityType;
import com.ricky.enums.impl.WeightUnit;
import com.ricky.types.common.Money;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className SaveCommodityCommand
 * @desc 新增商品command
 */
@Data
public class SaveCommodityCommand implements Command {

    private String name; // 商品名称
    private String description; // 商品描述
    private Money price; // 商品价格
    private Integer stock; // 商品库存量
    private CommodityType commodityType; // 商品状态
    private String mainImageUrl; // 主图URL

    // 分类与属性
    private Long categoryId; // 分类id
    private String brand; // 品牌

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
