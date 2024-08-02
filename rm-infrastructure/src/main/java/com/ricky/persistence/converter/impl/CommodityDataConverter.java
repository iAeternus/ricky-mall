package com.ricky.persistence.converter.impl;

import com.ricky.constants.JsonFormatConstant;
import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.persistence.converter.AggregateDataConverter;
import com.ricky.persistence.converter.decorator.MoneyDecorator;
import com.ricky.persistence.po.*;
import com.ricky.types.commodity.CommodityId;
import com.ricky.utils.CollUtils;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import javax.annotation.Resource;
import java.io.Serializable;
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
@Mapper(componentModel = "spring", uses = MoneyDecorator.class)
public abstract class CommodityDataConverter implements AggregateDataConverter<Commodity, CommodityId, CommodityPO> {

    @Resource
    private RelatedCommodityDataConverter relatedCommodityDataConverter;

    @Resource
    private SupplierDataConverter supplierDataConverter;

    @Resource
    private AttributeDataConverter attributeDataConverter;

    @Resource
    private CommodityImageDataConverter commodityImageDataConverter;

    @Override
    @Mappings({
            @Mapping(source = "id.value", target = "id"),
            @Mapping(source = "name.value", target = "name"),
            @Mapping(source = "description.value", target = "description"),
            @Mapping(source = "price.amount", target = "price"),
            @Mapping(source = "price.currency", target = "currencyCode"),
            @Mapping(source = "stock.value", target = "stock"),
            @Mapping(source = "weight.value", target = "weight"),
            @Mapping(source = "weight.unit", target = "weightUnit"),
            @Mapping(source = "type", target = "commodityType"),
            @Mapping(source = "categoryId.value", target = "categoryId"),
            @Mapping(source = "brand.name", target = "brandName"),
            @Mapping(source = "promotion.discountPrice.amount", target = "discountPrice"),
            @Mapping(source = "promotion.startTime", target = "promotionStartTime", dateFormat = JsonFormatConstant.LOCAL_DATE_TIME),
            @Mapping(source = "promotion.endTime", target = "promotionEndTime", dateFormat = JsonFormatConstant.LOCAL_DATE_TIME),
            @Mapping(source = "salesInformation.soldCount", target = "soldCount"),
            @Mapping(source = "salesInformation.createTime", target = "createTime", dateFormat = JsonFormatConstant.LOCAL_DATE_TIME),
            @Mapping(source = "salesInformation.updateTime", target = "updateTime", dateFormat = JsonFormatConstant.LOCAL_DATE_TIME),
            @Mapping(source = "seo.metaTitle", target = "metaTitle"),
            @Mapping(source = "seo.metaKeywords", target = "metaKeywords"),
            @Mapping(source = "seo.metaDescription", target = "metaDescription"),
    })
    public abstract CommodityPO convert(Commodity entity);

    @Override
    public Commodity convert(CommodityPO po, Map<String, List<BasePO>> relatedPOLists) {
        Commodity commodity = convert(po);
        if(CollUtils.isEmpty(relatedPOLists)) {
            return commodity;
        }

        List<CommodityImagePO> commodityImagePOS = CollUtils.listConvert(relatedPOLists.get(Commodity.RELATED_IMAGES), basePO -> (CommodityImagePO) basePO);
        List<AttributePO> attributePOS = CollUtils.listConvert(relatedPOLists.get(Commodity.RELATED_ATTRIBUTES), basePO -> (AttributePO) basePO);
        List<SupplierPO> supplierPOS = CollUtils.listConvert(relatedPOLists.get(Commodity.RELATED_SUPPLIERS), basePO -> (SupplierPO) basePO);
        List<RelatedCommodityPO> relatedCommodityPOS = CollUtils.listConvert(relatedPOLists.get(Commodity.RELATED_COMMODITIES), basePO -> (RelatedCommodityPO) basePO);

        commodity.setImages(CollUtils.listConvert(commodityImagePOS, commodityImageDataConverter::convert));
        commodity.setAttributes(CollUtils.listConvert(attributePOS, attributeDataConverter::convert));
        commodity.setSuppliers(CollUtils.listConvert(supplierPOS, supplierDataConverter::convert));
        commodity.setRelatedCommodities(CollUtils.listConvert(relatedCommodityPOS, relatedCommodityDataConverter::convert));

        return commodity;
    }

    @Mappings({
            @Mapping(source = "id", target = "id.value"),
            @Mapping(source = "name", target = "name.value"),
            @Mapping(source = "description", target = "description.value"),
            @Mapping(source = "price", target = "price.amount"),
            @Mapping(source = "currencyCode", target = "price.currency"),
            @Mapping(source = "stock", target = "stock.value"),
            @Mapping(source = "weight", target = "weight.value"),
            @Mapping(source = "weightUnit", target = "weight.unit"),
            @Mapping(source = "commodityType", target = "type"),
            @Mapping(source = "categoryId", target = "categoryId.value"),
            @Mapping(source = "brandName", target = "brand.name"),
            @Mapping(source = "discountPrice", target = "promotion.discountPrice.amount"),
            @Mapping(source = "currencyCode", target = "promotion.discountPrice.currency"),
            @Mapping(source = "promotionStartTime", target = "promotion.startTime", dateFormat = JsonFormatConstant.LOCAL_DATE_TIME),
            @Mapping(source = "promotionEndTime", target = "promotion.endTime", dateFormat = JsonFormatConstant.LOCAL_DATE_TIME),
            @Mapping(source = "soldCount", target = "salesInformation.soldCount"),
            @Mapping(source = "createTime", target = "salesInformation.createTime", dateFormat = JsonFormatConstant.LOCAL_DATE_TIME),
            @Mapping(source = "updateTime", target = "salesInformation.updateTime", dateFormat = JsonFormatConstant.LOCAL_DATE_TIME),
            @Mapping(source = "metaTitle", target = "seo.metaTitle"),
            @Mapping(source = "metaKeywords", target = "seo.metaKeywords"),
            @Mapping(source = "metaDescription", target = "seo.metaDescription"),
    })
    protected abstract Commodity convert(CommodityPO commodityPO);

    @Override
    @SuppressWarnings("unchecked")
    public <P extends BasePO> Map<String, List<P>> getAssociationPOLists(@NonNull Commodity aggregate) {
        Map<String, List<P>> map = new HashMap<>();
        List<CommodityImagePO> commodityImagePOS = CollUtils.listConvert(aggregate.getImages(), commodityImageDataConverter::convert);
        List<AttributePO> attributePOS = CollUtils.listConvert(aggregate.getAttributes(), attributeDataConverter::convert);
        List<SupplierPO> supplierPOS = CollUtils.listConvert(aggregate.getSuppliers(), supplierDataConverter::convert);
        List<RelatedCommodityPO> relatedCommodityPOS = CollUtils.listConvert(aggregate.getRelatedCommodities(), relatedCommodityDataConverter::convert);

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
    public void setAggregateId(@NonNull Commodity aggregate, @NonNull Serializable id) {
        aggregate.setId(new CommodityId((Long) id));
    }

}
