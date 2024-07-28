package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.domain.commodity.model.entity.Attribute;
import com.ricky.domain.commodity.model.entity.Image;
import com.ricky.domain.commodity.model.entity.Supplier;
import com.ricky.persistence.converter.AggregateDataConverter;
import com.ricky.persistence.po.*;
import com.ricky.types.commodity.*;
import com.ricky.types.common.Money;
import com.ricky.types.common.Weight;
import com.ricky.utils.CollUtils;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityDataConverter
 * @desc
 */
@Service
public class CommodityDataConverter implements AggregateDataConverter<Commodity, CommodityId, CommodityPO> {

    @Override
    public CommodityPO toPO(@NonNull Commodity entity) {
        CommodityId commodityId = entity.getId();
        Promotion promotion = entity.getPromotion();
        SalesInformation salesInformation = entity.getSalesInformation();
        Stock stock = entity.getStock();
        SEO seo = entity.getSeo();
        return CommodityPO.builder()
                .id(commodityId == null ? null : commodityId.getValue())
                .name(entity.getName().getValue())
                .description(entity.getDescription().getValue())
                .price(entity.getPrice().getAmount())
                .currencyCode(entity.getPrice().currencyCode())
                .stock(stock == null ? null : entity.getStock().getValue())
                .weight(entity.getWeight().getValue())
                .weightUnit(entity.getWeight().getUnit())
                .commodityType(entity.getType())
                .categoryId(entity.getCategoryId().getValue())
                .brandName(entity.getBrand().getName())
                .discountPrice(promotion == null ? null : promotion.getDiscountPrice().getAmount())
                .promotionStartTime(promotion == null ? null : promotion.getStartTime())
                .promotionEndTime(promotion == null ? null : promotion.getEndTime())
                .soldCount(salesInformation == null ? 0 : salesInformation.getSoldCount())
                .metaTitle(seo == null ? null : seo.getMetaTitle())
                .metaKeywords(seo == null ? null : seo.getMetaKeywords())
                .metaDescription(seo == null ? null : seo.getMetaDescription())
                .build();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Commodity toAggregate(@NonNull CommodityPO po, Map<String, List<? extends BasePO>> relatedPOLists) {
        List<CommodityImagePO> commodityImagePOS = (List<CommodityImagePO>) relatedPOLists.get(Commodity.RELATED_IMAGES);
        List<AttributePO> attributePOS = (List<AttributePO>) relatedPOLists.get(Commodity.RELATED_ATTRIBUTES);
        List<SupplierPO> supplierPOS = (List<SupplierPO>) relatedPOLists.get(Commodity.RELATED_SUPPLIERS);
        List<RelatedCommodityPO> relatedCommodityPOS = (List<RelatedCommodityPO>) relatedPOLists.get(Commodity.RELATED_COMMODITY_IDS);

        BigDecimal discountPrice = po.getDiscountPrice();

        return Commodity.builder()
                .id(new CommodityId(po.getId()))
                .name(new CommodityName(po.getName()))
                .description(new ProductDescription(po.getDescription()))
                .price(new Money(po.getPrice(), po.getCurrencyCode()))
                .stock(new Stock(po.getStock()))
                .weight(new Weight(po.getWeight(), po.getWeightUnit()))
                .type(po.getCommodityType())
                .categoryId(new CategoryId(po.getCategoryId()))
                .brand(new Brand(po.getBrandName()))
                .promotion(discountPrice == null ? null : new Promotion(
                        new Money(po.getDiscountPrice(), po.getCurrencyCode()),
                        po.getPromotionStartTime(),
                        po.getPromotionEndTime()
                ))
                .salesInformation(new SalesInformation(
                        po.getSoldCount(),
                        po.getCreateTime(),
                        po.getUpdateTime()
                ))
                .seo(new SEO(po.getMetaTitle(), po.getMetaKeywords(), po.getMetaDescription()))
                .images(CollUtils.listConvert(commodityImagePOS, imagePO -> new Image(
                        imagePO.getName(),
                        imagePO.getImageUrl(),
                        imagePO.getSizeInBytes()
                )))
                .attributes(CollUtils.listConvert(attributePOS, attributePO -> new Attribute(
                        new AttributeId(attributePO.getId()),
                        attributePO.getAttributeKey(),
                        attributePO.getAttributeValue()
                )))
                .suppliers(CollUtils.listConvert(supplierPOS, supplierPO -> new Supplier(
                        new SupplierId(supplierPO.getId()),
                        supplierPO.getName(),
                        supplierPO.getContact(),
                        supplierPO.getAddress()
                )))
                .relatedCommodityIds(CollUtils.listConvert(relatedCommodityPOS, relatedCommodityPO -> new CommodityId(relatedCommodityPO.getId())))
                .build();
    }

    @Override
    public void setAggregateId(@NonNull Commodity aggregate, @NonNull Serializable id) {
        aggregate.setId(new CommodityId((Long) id));
    }

}
