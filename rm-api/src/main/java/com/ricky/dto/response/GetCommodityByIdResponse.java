package com.ricky.dto.response;

import com.ricky.enums.impl.CommodityType;
import com.ricky.enums.impl.WeightUnit;
import com.ricky.types.common.Money;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/25
 * @className GetCommodityByIdResponse
 * @desc 根据id查询商品
 */
@Data
@Builder
public class GetCommodityByIdResponse implements Response {

    private Long id;
    private String name; // 商品名称
    private String description; // 商品描述
    private Money price; // 商品价格
    private Integer stock; // 商品库存量
    private CommodityType commodityType; // 商品状态
    private String mainImageUrl; // 主图URL
    private List<String> galleryImages; // 商品图片列表URL

    // 分类与属性
    private Long categoryId; // 分类id
    private String brand; // 品牌
    private Map<String, String> attributes; // 商品属性

    // 促销信息
    private Money discountPrice; // 折扣价
    private LocalDateTime promotionStartTime; // 促销开始时间
    private LocalDateTime promotionEndTime; // 促销结束时间
    private List<Long> relatedProducts; // 相关商品ID列表
    private List<Long> skuIds; // 对应的SKU ID列表

    // 销售信息
    private Integer soldCount; // 销售数量
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

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
