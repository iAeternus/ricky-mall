package com.ricky.domain.commodity.model.aggregate;

import com.ricky.constants.MessageConstant;
import com.ricky.domain.commodity.model.entity.Attribute;
import com.ricky.domain.commodity.model.entity.Image;
import com.ricky.domain.commodity.model.entity.RelatedCommodity;
import com.ricky.domain.commodity.model.entity.Supplier;
import com.ricky.enums.impl.CommodityType;
import com.ricky.exception.ForbiddenException;
import com.ricky.exception.InsufficientStockException;
import com.ricky.marker.Aggregate;
import com.ricky.types.commodity.*;
import com.ricky.types.common.Money;
import com.ricky.types.common.Weight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className Commodity
 * @desc 商品
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Commodity implements Aggregate<CommodityId> {

    private CommodityId id; // 商品ID
    private CommodityName name; // 商品名称
    private ProductDescription description; // 商品描述
    private Money price; // 商品价格
    private Stock stock; // 商品库存量
    private Weight weight; // 商品重量
    private CommodityType type; // 商品状态
    private CategoryId categoryId; // 分类ID
    private Brand brand; // 品牌
    private Promotion promotion; // 促销信息
    private SalesInformation salesInformation; // 销售信息
    private SEO seo; // SEO信息

    private List<Image> images; // 商品图片
    private List<Attribute> attributes; // 商品属性集合
    private List<Supplier> suppliers; // 供应商集合
    private List<RelatedCommodity> relatedCommodities; // 关联商品集合

    public static final String RELATED_IMAGES = "images";
    public static final String RELATED_ATTRIBUTES = "attributes";
    public static final String RELATED_SUPPLIERS = "suppliers";
    public static final String RELATED_COMMODITIES = "relatedCommodities";

    public Commodity(CommodityId id) {
        this.id = id;
    }

    /**
     * 更新商品价格
     *
     * @param newPrice 新的商品价格
     */
    public void updatePrice(Money newPrice) {
        this.price = newPrice;
    }

    /**
     * 扣减库存
     * @param delta 扣减量
     */
    public void reduceStock(Integer delta) {
        int newStock = this.stock.getValue() - delta;
        if(newStock < 0) {
            throw new ForbiddenException("库存不够");
        }
        this.stock = new Stock(newStock);
    }

    /**
     * 校验库存是否足够进行销售
     *
     * @param quantity 请求销售的数量
     * @return 如果库存足够返回true，否则返回false
     */
    public boolean checkStock(int quantity) {
        return this.stock.getValue() >= quantity;
    }

    /**
     * 减少库存量
     *
     * @param quantity 减少的数量
     */
    public void reduceStock(int quantity) {
        if (!checkStock(quantity)) {
            throw new InsufficientStockException(MessageConstant.INSUFFICIENT_STOCK);
        }
        this.stock = new Stock(this.stock.getValue() - quantity);
    }

}
