package com.ricky.assembler;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.domain.commodity.model.entity.RelatedCommodity;
import com.ricky.dto.command.ModifyCommodityCommand;
import com.ricky.dto.command.SaveCommodityCommand;
import com.ricky.dto.response.GetCommodityByIdResponse;
import com.ricky.types.commodity.*;
import com.ricky.types.common.Weight;
import com.ricky.utils.CollUtils;
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
                .weight(new Weight(command.getWeight(), command.getWeightUnit()))
                .categoryId(new CategoryId(command.getCategoryId()))
                .brand(new Brand(command.getBrandName()))
                .seo(new SEO(command.getMetaTitle(), command.getMetaKeywords(), command.getMetaDescription()))
                .images(command.getImages())
                .attributes(command.getAttributes())
                .suppliers(command.getSuppliers())
                .relatedCommodities(command.getRelatedCommodities())
                .build();
    }

    public Commodity toCommodity(ModifyCommodityCommand command) {
        return Commodity.builder()
                .id(new CommodityId(command.getId()))
                .name(new CommodityName(command.getName()))
                .description(new ProductDescription(command.getDescription()))
                .price(command.getPrice())
                .weight(new Weight(command.getWeight(), command.getWeightUnit()))
                .categoryId(new CategoryId(command.getCategoryId()))
                .brand(new Brand(command.getName()))
                .seo(new SEO(command.getMetaTitle(), command.getMetaKeywords(), command.getMetaDescription()))
                .images(command.getImages())
                .attributes(command.getAttributes())
                .suppliers(command.getSuppliers())
                .relatedCommodities(command.getRelatedCommodities())
                .build();
    }

    // private List<Commodity> mapCommodityList(List<Long> commodityIds) {
    //     return commodityIds.stream()
    //             .map(commodityId -> new Commodity(new CommodityId(commodityId)))
    //             .toList();
    // }

    public GetCommodityByIdResponse toGetCommodityByIdResponse(Commodity commodity) {
        Promotion promotion = commodity.getPromotion();
        return GetCommodityByIdResponse.builder()
                .id(commodity.getId().getValue())
                .name(commodity.getName().getValue())
                .description(commodity.getDescription().getValue())
                .price(commodity.getPrice())
                .stock(commodity.getStock().getValue())
                .weight(commodity.getWeight().getValue())
                .weightUnit(commodity.getWeight().getUnit())
                .commodityType(commodity.getType())
                .categoryId(commodity.getCategoryId().getValue())
                .brandName(commodity.getBrand().getName())
                .discountPrice(promotion == null ? null : promotion.getDiscountPrice())
                .promotionStartTime(promotion == null ? null : promotion.getStartTime())
                .promotionEndTime(promotion == null ? null : promotion.getEndTime())
                .soldCount(commodity.getSalesInformation().getSoldCount())
                .createTime(commodity.getSalesInformation().getCreateTime())
                .updateTime(commodity.getSalesInformation().getUpdateTime())
                .metaTitle(commodity.getSeo().getMetaTitle())
                .metaKeywords(commodity.getSeo().getMetaKeywords())
                .metaDescription(commodity.getSeo().getMetaDescription())
                .images(commodity.getImages())
                .attributes(commodity.getAttributes())
                .suppliers(commodity.getSuppliers())
                .relatedCommodities(CollUtils.listConvert(commodity.getRelatedCommodities(), RelatedCommodity::getRelatedCommodityId))
                .build();
    }

}
