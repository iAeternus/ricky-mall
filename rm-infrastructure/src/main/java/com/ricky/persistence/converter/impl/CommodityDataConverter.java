package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.AssociatedCommodityPO;
import com.ricky.persistence.po.AttributePO;
import com.ricky.persistence.po.CommodityPO;
import com.ricky.persistence.po.GalleryImagePO;
import com.ricky.types.commodity.*;
import com.ricky.types.common.Money;
import com.ricky.types.common.Weight;
import com.ricky.utils.CollUtils;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityDataConverter
 * @desc
 */
@Service
public class CommodityDataConverter implements DataConverter<Commodity, CommodityId, CommodityPO> {

    @Override
    public CommodityPO toPO(@NonNull Commodity entity) {
        CommodityId commodityId = entity.getId();
        PromotionInformation promotionInformation = entity.getPromotionInformation();
        SalesInformation salesInformation = entity.getSalesInformation();
        return CommodityPO.builder()
                .id(commodityId == null ? null : commodityId.getValue())
                .name(entity.getName().getValue())
                .description(entity.getDescription().getValue())
                .price(entity.getPrice().getAmount())
                .currencyCode(entity.getPrice().currencyCode())
                .stock(entity.getStock().getValue())
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
                .metaTitle(entity.getSeo().getMetaTitle())
                .metaKeywords(entity.getSeo().getMetaKeywords())
                .metaDescription(entity.getSeo().getMetaDescription())
                .build();
    }

    @Override
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
