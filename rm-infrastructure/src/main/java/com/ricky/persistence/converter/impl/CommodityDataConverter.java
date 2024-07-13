package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.AssociatedCommodityPO;
import com.ricky.persistence.po.AttributePO;
import com.ricky.persistence.po.CommodityPO;
import com.ricky.persistence.po.GalleryImagePO;
import com.ricky.types.commodity.*;
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
        return CommodityPO.builder()
                .id(entity.getId().getValue())
                .name(entity.getName().getValue())
                .description(entity.getDescription().getValue())
                .price(entity.getPrice())
                .stock(entity.getStock().getValue())
                .commodityType(entity.getType())
                .mainImageUrl(entity.getPictureInformation().getMainImageUrl())
                .categoryId(entity.getCategoryId().getValue())
                .brand(entity.getBrand().getName())
                .originalPrice(entity.getPromotionInformation().getOriginalPrice())
                .promotionStartTime(entity.getPromotionInformation().getStartTime())
                .promotionEndTime(entity.getPromotionInformation().getEndTime())
                .soldCount(entity.getSalesInformation().getSoldCount())
                .weight(entity.getShippingInformation().getWeight().getValue())
                .weightUnit(entity.getShippingInformation().getWeight().getUnit())
                .shippingType(entity.getShippingInformation().getType())
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
                .price(po.getPrice())
                .stock(new Stock(po.getStock()))
                .type(po.getCommodityType())
                .categoryId(new CategoryId(po.getCategoryId()))
                .brand(new Brand(po.getBrand()))
                .promotionInformation(new PromotionInformation(
                        po.getOriginalPrice(),
                        po.getDiscountPrice(),
                        po.getPromotionStartTime(),
                        po.getPromotionEndTime()
                ))
                .salesInformation(new SalesInformation(
                        po.getSoldCount(),
                        po.getCreateTime(),
                        po.getUpdateTime()
                ))
                .shippingInformation(new ShippingInformation(
                        new Weight(po.getWeight(), po.getWeightUnit()),
                        po.getShippingType()
                ))
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
                        .collect(Collectors.toMap(AttributePO::getKey, AttributePO::getValue))
        ));
        commodity.setRelatesInformation(new RelatesInformation(
                CollUtils.listConvert(associatedCommodityPOS, AssociatedCommodityPO::getRelatedCommodityId),
                CollUtils.listConvert(associatedCommodityPOS, AssociatedCommodityPO::getSkuIds)
        ));
        return commodity;
    }

}
