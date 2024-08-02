package com.ricky.assembler;

import com.ricky.assembler.decorator.RelatedCommodityDecorator;
import com.ricky.constants.JsonFormatConstant;
import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.dto.command.ModifyCommodityCommand;
import com.ricky.dto.command.SaveCommodityCommand;
import com.ricky.dto.response.GetCommodityByIdResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityAssembler
 * @desc
 */
@Mapper(componentModel = "spring", uses = RelatedCommodityDecorator.class)
public interface CommodityAssembler {

    @Mappings({
            @Mapping(target = "name.value", source = "name"),
            @Mapping(target = "description.value", source = "description"),
            @Mapping(target = "stock.value", source = "stock"),
            @Mapping(target = "weight.value", source = "weight"),
            @Mapping(target = "weight.unit", source = "weightUnit"),
            @Mapping(target = "categoryId.value", source = "categoryId"),
            @Mapping(target = "brand.name", source = "brandName"),
            @Mapping(target = "seo.metaTitle", source = "metaTitle"),
            @Mapping(target = "seo.metaKeywords", source = "metaKeywords"),
            @Mapping(target = "seo.metaDescription", source = "metaDescription"),
    })
    Commodity convert(SaveCommodityCommand command);

    @Mappings({
            @Mapping(target = "id.value", source = "id"),
            @Mapping(target = "name.value", source = "name"),
            @Mapping(target = "description.value", source = "description"),
            @Mapping(target = "weight.value", source = "weight"),
            @Mapping(target = "weight.unit", source = "weightUnit"),
            @Mapping(target = "categoryId.value", source = "categoryId"),
            @Mapping(target = "brand.name", source = "brandName"),
            @Mapping(target = "seo.metaTitle", source = "metaTitle"),
            @Mapping(target = "seo.metaKeywords", source = "metaKeywords"),
            @Mapping(target = "seo.metaDescription", source = "metaDescription"),
    })
    Commodity convert(ModifyCommodityCommand command);

    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "name", source = "name.value"),
            @Mapping(target = "description", source = "description.value"),
            @Mapping(target = "stock", source = "stock.value"),
            @Mapping(target = "weight", source = "weight.value"),
            @Mapping(target = "weightUnit", source = "weight.unit"),
            @Mapping(target = "commodityType", source = "type"),
            @Mapping(target = "categoryId", source = "categoryId.value"),
            @Mapping(target = "brandName", source = "brand.name"),
            @Mapping(target = "discountPrice", source = "promotion.discountPrice"),
            @Mapping(target = "promotionStartTime", source = "promotion.startTime", dateFormat = JsonFormatConstant.LOCAL_DATE_TIME),
            @Mapping(target = "promotionEndTime", source = "promotion.endTime", dateFormat = JsonFormatConstant.LOCAL_DATE_TIME),
            @Mapping(target = "soldCount", source = "salesInformation.soldCount"),
            @Mapping(target = "createTime", source = "salesInformation.createTime"),
            @Mapping(target = "updateTime", source = "salesInformation.updateTime"),
            @Mapping(target = "metaTitle", source = "seo.metaTitle"),
            @Mapping(target = "metaKeywords", source = "seo.metaKeywords"),
            @Mapping(target = "metaDescription", source = "seo.metaDescription"),
    })
    GetCommodityByIdResponse convert(Commodity commodity);

}
