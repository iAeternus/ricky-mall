package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.entity.Attribute;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.AttributePO;
import com.ricky.types.commodity.AttributeId;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/29
 * @className AttributeDataConverter
 * @desc
 */
@Service
public class AttributeDataConverter implements DataConverter<Attribute, AttributeId, AttributePO> {
    @Override
    public AttributePO toPO(@NonNull Attribute entity) {
        return AttributePO.builder()
                .attributeKey(entity.getAttributesKey())
                .attributeValue(entity.getAttributesValue())
                .build();
    }

    @Override
    public Attribute toEntity(@NonNull AttributePO po) {
        return Attribute.builder()
                .id(new AttributeId(po.getId()))
                .attributesKey(po.getAttributeKey())
                .attributesValue(po.getAttributeValue())
                .build();
    }
}