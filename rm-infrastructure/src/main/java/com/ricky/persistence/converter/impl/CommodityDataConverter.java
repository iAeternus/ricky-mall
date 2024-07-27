package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.persistence.converter.AggregateDataConverter;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.*;
import com.ricky.types.commodity.*;
import com.ricky.types.common.Money;
import com.ricky.types.common.Weight;
import com.ricky.utils.CollUtils;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        PromotionInformation promotionInformation = entity.getPromotionInformation();
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
                .commodityType(entity.getType())
                .mainImageUrl(entity.getPictureInformation().getMainImageUrl())
                .categoryId(entity.getCategoryId().getValue())
                .brand(entity.getBrand().getName())
                .discountPrice(promotionInformation == null ? null : promotionInformation.getDiscountPrice().getAmount())
                .promotionStartTime(promotionInformation == null ? null : promotionInformation.getStartTime())
                .promotionEndTime(promotionInformation == null ? null : promotionInformation.getEndTime())
                .soldCount(salesInformation == null ? 0 : salesInformation.getSoldCount())
                .weight(entity.getShippingInformation().getWeight().getValue())
                .weightUnit(entity.getShippingInformation().getWeight().getUnit())
                .supplierId(entity.getSupplierInformation().getSupplierId())
                .metaTitle(seo == null ? null : seo.getMetaTitle())
                .metaKeywords(seo == null ? null : seo.getMetaKeywords())
                .metaDescription(seo == null ? null : seo.getMetaDescription())
                .build();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Commodity toAggregate(@NonNull CommodityPO po, Map<Class<?>, List<? extends BasePO>> relatedPOLists) {
        List<GalleryImagePO> galleryImagePOS = (List<GalleryImagePO>) relatedPOLists.get(GalleryImagePO.class);
        List<AttributePO> attributePOS = (List<AttributePO>) relatedPOLists.get(AttributePO.class);
        List<AssociatedCommodityPO> associatedCommodityPOS = (List<AssociatedCommodityPO>) relatedPOLists.get(AssociatedCommodityPO.class);
        return Commodity.builder()
                .id(new CommodityId(po.getId()))
                .name(new CommodityName(po.getName()))
                .description(new ProductDescription(po.getDescription()))
                .price(new Money(po.getPrice(), po.getCurrencyCode()))
                .stock(new Stock(po.getStock()))
                .type(po.getCommodityType())
                .pictureInformation(new PictureInformation(
                        po.getMainImageUrl(),
                        CollUtils.listConvert(galleryImagePOS, GalleryImagePO::getImageUrl)
                ))
                .categoryId(new CategoryId(po.getCategoryId()))
                .brand(new Brand(po.getBrand()))
                .attributes(new Attributes(
                        attributePOS.stream()
                                .collect(Collectors.toMap(AttributePO::getAttributeKey, AttributePO::getAttributeValue))
                ))
                .promotionInformation(po.getDiscountPrice() == null ? null : new PromotionInformation(
                        new Money(po.getDiscountPrice(), po.getCurrencyCode()),
                        po.getPromotionStartTime(),
                        po.getPromotionEndTime()
                ))
                .relatesInformation(new RelatesInformation(
                        CollUtils.listConvert(associatedCommodityPOS, AssociatedCommodityPO::getRelatedCommodityId),
                        CollUtils.listConvert(associatedCommodityPOS, AssociatedCommodityPO::getSkuId)
                ))
                .salesInformation(new SalesInformation(
                        po.getSoldCount(),
                        po.getCreateTime(),
                        po.getUpdateTime()
                ))
                .shippingInformation(new ShippingInformation(new Weight(po.getWeight(), po.getWeightUnit())))
                .supplierInformation(new SupplierInformation(po.getSupplierId()))
                .seo(new SEO(po.getMetaTitle(), po.getMetaKeywords(), po.getMetaDescription()))
                .build();
    }

    // @Override
    public Commodity toEntity(@NonNull CommodityPO po) {
        return Commodity.builder()
                .id(new CommodityId(po.getId()))
                .name(new CommodityName(po.getName()))
                .description(new ProductDescription(po.getDescription()))
                .price(new Money(po.getPrice(), po.getCurrencyCode()))
                .stock(new Stock(po.getStock()))
                .type(po.getCommodityType())
                .categoryId(new CategoryId(po.getCategoryId()))
                .brand(new Brand(po.getBrand()))
                .promotionInformation(po.getDiscountPrice() == null ? null : new PromotionInformation(
                        new Money(po.getDiscountPrice(), po.getCurrencyCode()),
                        po.getPromotionStartTime(),
                        po.getPromotionEndTime()
                ))
                .salesInformation(new SalesInformation(
                        po.getSoldCount(),
                        po.getCreateTime(),
                        po.getUpdateTime()
                ))
                .shippingInformation(new ShippingInformation(new Weight(po.getWeight(), po.getWeightUnit())))
                .supplierInformation(new SupplierInformation(po.getSupplierId()))
                .seo(new SEO(po.getMetaTitle(), po.getMetaKeywords(), po.getMetaDescription()))
                .build();
    }

    public Commodity toEntity(
            @NonNull CommodityPO po,
            @NonNull List<GalleryImagePO> galleryImagePOS,
            @NonNull List<AttributePO> attributePOS,
            @NonNull List<AssociatedCommodityPO> associatedCommodityPOS
    ) {
        Commodity commodity = toEntity(po);
        commodity.setPictureInformation(new PictureInformation(
                po.getMainImageUrl(),
                CollUtils.listConvert(galleryImagePOS, GalleryImagePO::getImageUrl)
        ));
        commodity.setAttributes(new Attributes(
                attributePOS.stream()
                        .collect(Collectors.toMap(AttributePO::getAttributeKey, AttributePO::getAttributeValue))
        ));
        commodity.setRelatesInformation(new RelatesInformation(
                CollUtils.listConvert(associatedCommodityPOS, AssociatedCommodityPO::getRelatedCommodityId),
                CollUtils.listConvert(associatedCommodityPOS, AssociatedCommodityPO::getSkuId)
        ));
        return commodity;
    }

}
