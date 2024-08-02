package com.ricky.dto.command;

import com.ricky.domain.commodity.model.entity.Attribute;
import com.ricky.domain.commodity.model.entity.Image;
import com.ricky.domain.commodity.model.entity.RelatedCommodity;
import com.ricky.domain.commodity.model.entity.Supplier;
import com.ricky.enums.impl.WeightUnit;
import com.ricky.types.common.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className SaveCommodityCommand
 * @desc 新增商品command
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveCommodityCommand implements Command {

    /*
    sample input
{
  "attributes": [
    {
      "attributeKey": "color",
      "attributeValue": "red"
    },
    {
      "attributeKey": "size",
      "attributeValue": "small"
    }
  ],
  "brandName": "xxx",
  "categoryId": 1,
  "description": "用过都说好",
  "images": [
    {
      "name": "图1",
      "sizeInBytes": 1024,
      "url": "https://bilibili.com"
    },
    {
      "name": "图2",
      "sizeInBytes": 1024,
      "url": "https://bilibili.com"
    },
    {
      "name": "图3",
      "sizeInBytes": 1024,
      "url": "https://bilibili.com"
    }
  ],
  "metaDescription": "xxx",
  "metaKeywords": "xxx",
  "metaTitle": "xxx",
  "name": "xxx",
  "price": {
    "amount": 1000,
    "currency": "CNY"
  },
  "relatedCommodities": [
    {
      "relatedCommodityId": 10
    },
    {
      "relatedCommodityId": 11
    },
    {
      "relatedCommodityId": 12
    },
    {
      "relatedCommodityId": 13
    }
  ],
  "stock": 1000,
  "suppliers": [
    {
      "address": "xxx",
      "contact": "xxx",
      "id": {
        "value": 1
      },
      "name": "xxx"
    }
  ],
  "weight": 10,
  "weightUnit": "KILOGRAM"
}
     */

    private String name; // 商品名称
    private String description; // 商品描述
    private Money price; // 商品价格
    private Integer stock; // 商品库存量
    private Double weight; // 商品重量值
    private WeightUnit weightUnit; // 商品重量单位
    private Long categoryId; // 分类id
    private String brandName; // 品牌名
    private String metaTitle; // SEO标题
    private String metaKeywords; // SEO关键词
    private String metaDescription; // SEO描述

    private List<Image> images; // 商品图片
    private List<Attribute> attributes; // 商品属性集合
    private List<Supplier> suppliers; // 供应商集合
    private List<RelatedCommodity> relatedCommodities; // 关联商品集合

}
