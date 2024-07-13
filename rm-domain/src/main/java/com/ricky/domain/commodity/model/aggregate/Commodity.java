package com.ricky.domain.commodity.model.aggregate;

import com.ricky.constants.MessageConstant;
import com.ricky.enums.impl.CommodityType;
import com.ricky.exception.InsufficientStockException;
import com.ricky.marker.Aggregate;
import com.ricky.types.commodity.*;
import com.ricky.types.common.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private CommodityType type; // 商品状态
    private PictureInformation pictureInformation; // 图片信息

    // 分类与属性
    private CategoryId categoryId; // 分类ID
    private Brand brand; // 品牌
    private Attributes attributes; // 商品属性键值对

    // 各种信息
    private PromotionInformation promotionInformation; // 促销信息
    private RelatesInformation relatesInformation; // 关联信息
    private SalesInformation salesInformation; // 销售信息
    private ShippingInformation shippingInformation; // 发货信息
    private SupplierInformation supplierInformation; // 供应商信息

    private SEO seo; // SEO信息

    /**
     * 更新商品价格
     *
     * @param newPrice 新的商品价格
     */
    public void updatePrice(Money newPrice) {
        this.price = newPrice;
    }

    /**
     * 更新商品库存
     *
     * @param newStock 新的库存量
     */
    public void updateStock(Stock newStock) {
        this.stock = newStock;
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
