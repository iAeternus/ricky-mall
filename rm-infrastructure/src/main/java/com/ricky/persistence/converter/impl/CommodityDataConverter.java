package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.persistence.converter.AggregateDataConverter;
import com.ricky.persistence.po.*;
import com.ricky.types.commodity.*;
import com.ricky.types.common.Money;
import com.ricky.types.common.Weight;
import com.ricky.utils.CollUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
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
@RequiredArgsConstructor
public class CommodityDataConverter implements AggregateDataConverter<Commodity, CommodityId, CommodityPO> {

    private final RelatedCommodityDataConverter relatedCommodityDataConverter;
    private final SupplierDataConverter supplierDataConverter;
    private final AttributeDataConverter attributeDataConverter;
    private final CommodityImageDataConverter commodityImageDataConverter;

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
    public <P extends BasePO> Map<String, List<P>> toRelatedPOLists(@NonNull Commodity aggregate) {
        Map<String, List<P>> map = new HashMap<>();
        List<CommodityImagePO> commodityImagePOS = CollUtils.listConvert(aggregate.getImages(), commodityImageDataConverter::toPO);
        List<AttributePO> attributePOS = CollUtils.listConvert(aggregate.getAttributes(), attributeDataConverter::toPO);
        List<SupplierPO> supplierPOS = CollUtils.listConvert(aggregate.getSuppliers(), supplierDataConverter::toPO);
        List<RelatedCommodityPO> relatedCommodityPOS = CollUtils.listConvert(aggregate.getRelatedCommodities(), relatedCommodityDataConverter::toPO);

        commodityImagePOS.forEach(commodityImagePO -> commodityImagePO.setCommodityId(aggregate.getId().getValue()));
        attributePOS.forEach(commodityImagePO -> commodityImagePO.setCommodityId(aggregate.getId().getValue()));
        supplierPOS.forEach(commodityImagePO -> commodityImagePO.setCommodityId(aggregate.getId().getValue()));
        relatedCommodityPOS.forEach(commodityImagePO -> commodityImagePO.setCommodityId(aggregate.getId().getValue()));

        map.put(Commodity.RELATED_IMAGES, (List<P>) commodityImagePOS);
        map.put(Commodity.RELATED_ATTRIBUTES, (List<P>) attributePOS);
        map.put(Commodity.RELATED_SUPPLIERS, (List<P>) supplierPOS);
        map.put(Commodity.RELATED_COMMODITIES, (List<P>) relatedCommodityPOS);
        return map;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <P extends BasePO> Commodity toAggregate(@NonNull CommodityPO po, Map<String, List<P>> relatedPOLists) {
        List<CommodityImagePO> commodityImagePOS = (List<CommodityImagePO>) relatedPOLists.get(Commodity.RELATED_IMAGES);
        List<AttributePO> attributePOS = (List<AttributePO>) relatedPOLists.get(Commodity.RELATED_ATTRIBUTES);
        List<SupplierPO> supplierPOS = (List<SupplierPO>) relatedPOLists.get(Commodity.RELATED_SUPPLIERS);
        List<RelatedCommodityPO> relatedCommodityPOS = (List<RelatedCommodityPO>) relatedPOLists.get(Commodity.RELATED_COMMODITIES);

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
                .images(CollUtils.listConvert(commodityImagePOS, commodityImageDataConverter::toEntity))
                .attributes(CollUtils.listConvert(attributePOS, attributeDataConverter::toEntity))
                .suppliers(CollUtils.listConvert(supplierPOS, supplierDataConverter::toEntity))
                .relatedCommodities(CollUtils.listConvert(relatedCommodityPOS, relatedCommodityDataConverter::toEntity))
                .build();
    }

    @Override
    public void setAggregateId(@NonNull Commodity aggregate, @NonNull Serializable id) {
        aggregate.setId(new CommodityId((Long) id));
    }

}
