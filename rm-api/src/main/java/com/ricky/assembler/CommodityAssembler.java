package com.ricky.assembler;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.dto.command.ModifyCommodityCommand;
import com.ricky.dto.command.SaveCommodityCommand;
import com.ricky.dto.response.GetCommodityByIdResponse;
import com.ricky.types.commodity.*;
import com.ricky.types.common.Weight;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityAssembler
 * @desc
 */
@Service
public class CommodityAssembler {

    public Commodity toCommodity(SaveCommodityCommand command) {
        return Commodity.builder()
                .name(new CommodityName(command.getName()))
                .description(new ProductDescription(command.getDescription()))
                .price(command.getPrice())
                .stock(new Stock(command.getStock()))
                .type(command.getCommodityType())
                .pictureInformation(new PictureInformation(command.getMainImageUrl(), null))
                .categoryId(new CategoryId(command.getCategoryId()))
                .brand(new Brand(command.getBrand()))
                .shippingInformation(new ShippingInformation(new Weight(command.getWeight(), command.getWeightUnit())))
                .supplierInformation(new SupplierInformation(command.getSupplierId()))
                .seo(new SEO(
                        command.getMetaTitle(),
                        command.getMetaKeywords(),
                        command.getMetaDescription()
                ))
                .build();
    }

    public Commodity toCommodity(ModifyCommodityCommand command) {
        return Commodity.builder()
                .id(new CommodityId(command.getId()))
                .name(new CommodityName(command.getName()))
                .description(new ProductDescription(command.getDescription()))
                .price(command.getPrice())
                .pictureInformation(new PictureInformation(command.getMainImageUrl(), null))
                .categoryId(new CategoryId(command.getCategoryId()))
                .brand(new Brand(command.getBrand()))
                .shippingInformation(new ShippingInformation(new Weight(command.getWeight(), command.getWeightUnit())))
                .supplierInformation(new SupplierInformation(command.getSupplierId()))
                .build();
    }

    public GetCommodityByIdResponse toGetCommodityByIdResponse(Commodity commodity) {
        PromotionInformation promotionInformation = commodity.getPromotionInformation();
        return GetCommodityByIdResponse.builder()
                .id(commodity.getId().getValue())
                .name(commodity.getName().getValue())
                .description(commodity.getDescription().getValue())
                .price(commodity.getPrice())
                .stock(commodity.getStock().getValue())
                .commodityType(commodity.getType())
                .mainImageUrl(commodity.getPictureInformation().getMainImageUrl())
                .galleryImages(commodity.getPictureInformation().getGalleryImages())
                .categoryId(commodity.getCategoryId().getValue())
                .brand(commodity.getBrand().getName())
                .attributes(commodity.getAttributes().getValue())
                .discountPrice(promotionInformation == null ? null : promotionInformation.getDiscountPrice())
                .promotionStartTime(promotionInformation == null ? null : promotionInformation.getStartTime())
                .promotionEndTime(promotionInformation == null ? null : promotionInformation.getEndTime())
                .relatedProducts(commodity.getRelatesInformation().getRelatedProducts())
                .skuIds(commodity.getRelatesInformation().getSkuIds())
                .soldCount(commodity.getSalesInformation().getSoldCount())
                .createTime(commodity.getSalesInformation().getCreateTime())
                .updateTime(commodity.getSalesInformation().getUpdateTime())
                .weight(commodity.getShippingInformation().getWeight().getValue())
                .weightUnit(commodity.getShippingInformation().getWeight().getUnit())
                .supplierId(commodity.getSupplierInformation().getSupplierId())
                .metaTitle(commodity.getSeo().getMetaTitle())
                .metaKeywords(commodity.getSeo().getMetaKeywords())
                .metaDescription(commodity.getSeo().getMetaDescription())
                .build();
    }

}
