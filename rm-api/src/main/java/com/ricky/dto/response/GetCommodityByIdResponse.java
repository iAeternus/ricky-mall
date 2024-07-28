package com.ricky.dto.response;

import com.ricky.domain.commodity.model.entity.Attribute;
import com.ricky.domain.commodity.model.entity.Image;
import com.ricky.domain.commodity.model.entity.Supplier;
import com.ricky.enums.impl.CommodityType;
import com.ricky.enums.impl.WeightUnit;
import com.ricky.types.common.Money;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
    private Double weight; // 商品重量值
    private WeightUnit weightUnit; // 商品重量单位
    private CommodityType commodityType; // 商品状态
    private Long categoryId; // 分类id
    private String brandName; // 品牌名
    private Money discountPrice; // 折扣价
    private LocalDateTime promotionStartTime; // 促销开始时间
    private LocalDateTime promotionEndTime; // 促销结束时间
    private Integer soldCount; // 销售数量
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
    private String metaTitle; // SEO标题
    private String metaKeywords; // SEO关键词
    private String metaDescription; // SEO描述

    private List<Image> images; // 商品图片
    private List<Attribute> attributes; // 商品属性
    private List<Supplier> suppliers; // 供应商集合
    private List<Long> relatedCommodities; // 关联商品集合

}
