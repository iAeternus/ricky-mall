package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.entity.Image;
import com.ricky.persistence.converter.AssociationDataConverter;
import com.ricky.persistence.converter.decorator.ForeignKeyDecorator;
import com.ricky.persistence.po.CommodityImagePO;
import com.ricky.types.commodity.ImageId;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/29
 * @className CommodityImageDataConverter
 * @desc
 */
@Mapper(componentModel = "spring", uses = ForeignKeyDecorator.class)
public abstract class CommodityImageDataConverter implements AssociationDataConverter<Image, ImageId, CommodityImagePO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "entity.id.value"),
            @Mapping(target = "name", source = "entity.name"),
            @Mapping(target = "commodityId", source = "foreignKey"),
            @Mapping(target = "imageUrl", source = "entity.url"),
            @Mapping(target = "sizeInBytes", source = "entity.sizeInBytes"),
    })
    public abstract CommodityImagePO convert(Image entity, Serializable foreignKey);

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "imageUrl", source = "url"),
    })
    public abstract CommodityImagePO convert(Image entity);

    @Override
    @Mappings({
            @Mapping(target = "id.value", source = "id"),
            @Mapping(target = "url", source = "imageUrl"),
    })
    public abstract Image convert(CommodityImagePO po);

}
