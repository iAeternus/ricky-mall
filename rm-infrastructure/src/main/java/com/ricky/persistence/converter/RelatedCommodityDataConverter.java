package com.ricky.persistence.converter;

import com.ricky.domain.commodity.model.entity.RelatedCommodity;
import com.ricky.persistence.converter.AssociationDataConverter;
import com.ricky.persistence.converter.decorator.ForeignKeyDecorator;
import com.ricky.persistence.po.RelatedCommodityPO;
import com.ricky.types.commodity.RelatedCommodityId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.io.Serializable;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/29
 * @className RelatedCommodityDataConverter
 * @desc
 */
@Mapper(componentModel = "spring", uses = ForeignKeyDecorator.class)
public abstract class RelatedCommodityDataConverter implements AssociationDataConverter<RelatedCommodity, RelatedCommodityId, RelatedCommodityPO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "entity.id.value"),
            @Mapping(target = "commodityId", source = "foreignKey")
    })
    public abstract RelatedCommodityPO convert(RelatedCommodity entity, Serializable foreignKey);

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id.value"),
    })
    public abstract RelatedCommodityPO convert(RelatedCommodity entity);

    @Override
    @Mappings({
            @Mapping(target = "id.value", source = "id"),
    })
    public abstract RelatedCommodity convert(RelatedCommodityPO po);

}
