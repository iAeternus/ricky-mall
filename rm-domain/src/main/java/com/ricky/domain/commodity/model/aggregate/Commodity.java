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
import com.ricky.types.store.StoreId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private Weight weight; // 商品重量
    private CommodityType type; // 商品状态
    private SalesInformation salesInformation; // 销售信息
    private SEO seo; // SEO信息

    // private Stock stock; // 商品库存量
    private Promotion promotion; // 促销信息 TODO

    private CategoryId categoryId; // 分类ID
    private StoreId storeId; // 店铺id

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
     * 减少库存量
     *
     * @param delta 减少的数量
     */
    public void reduceStock(Integer delta) {
        if (this.stock.getValue() < delta) {
            throw new InsufficientStockException(MessageConstant.INSUFFICIENT_STOCK);
        }
        this.stock = new Stock(this.stock.getValue() - delta);
    }

    /**
     * 变更商品价格，delta为正则加价，反之降价
     * @param delta 变更的数额
     */
    public void updatePrice(BigDecimal delta) {
        if(BigDecimal.ZERO.compareTo(delta) > 0 && delta.abs().compareTo(this.price.getAmount()) > 0) {
            throw new ForbiddenException(MessageConstant.LESS_THAN_ZERO);
        }
        this.price = new Money(this.price.getAmount().add(delta), this.price.getCurrency());
    }

}
