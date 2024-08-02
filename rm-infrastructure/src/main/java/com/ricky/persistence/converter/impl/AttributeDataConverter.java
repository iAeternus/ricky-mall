package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.entity.Attribute;
import com.ricky.persistence.converter.AssociationDataConverter;
import com.ricky.persistence.converter.decorator.ForeignKeyDecorator;
import com.ricky.persistence.po.AttributePO;
import com.ricky.types.commodity.AttributeId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.io.Serializable;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/29
 * @className AttributeDataConverter
 * @desc
 */
@Mapper(componentModel = "spring", uses = ForeignKeyDecorator.class)
public abstract class AttributeDataConverter implements AssociationDataConverter<Attribute, AttributeId, AttributePO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "entity.id.value"),
            @Mapping(target = "commodityId", source = "foreignKey"),
            @Mapping(target = "attributeKey", source = "entity.attributeKey"),
            @Mapping(target = "attributeValue", source = "entity.attributeValue"),
    })
    public abstract AttributePO convert(Attribute entity, Serializable foreignKey);

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id.value")
    })
    public abstract AttributePO convert(Attribute entity);

    @Override
    @Mappings({
            @Mapping(target = "id.value", source = "id")
    })
    public abstract Attribute convert(AttributePO po);

}
