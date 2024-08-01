package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.entity.Attribute;
import com.ricky.persistence.po.AttributePO;
import com.ricky.types.commodity.AttributeId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className AttributeDataConverterTest
 * @desc
 */
@SpringBootTest
public class AttributeDataConverterTest {

    @Autowired
    private AttributeDataConverter attributeDataConverter;

    @Test
    public void convert() {
        // Given
        Attribute attribute = new Attribute(new AttributeId(1L), "size", "small");
        Long commodityId = 1L;

        // When
        AttributePO attributePO = attributeDataConverter.convert(attribute, commodityId);
        Attribute attribute1 = attributeDataConverter.convert(attributePO);

        // Then
        assertThat(attribute).isEqualTo(attribute1);
    }

}
