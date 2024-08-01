package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.entity.RelatedCommodity;
import com.ricky.persistence.po.RelatedCommodityPO;
import com.ricky.types.commodity.CommodityId;
import com.ricky.types.commodity.RelatedCommodityId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className RelatedCommodityDataConverterTest
 * @desc
 */
@SpringBootTest
public class RelatedCommodityDataConverterTest {

    @Autowired
    private RelatedCommodityDataConverter relatedCommodityDataConverter;

    @Test
    public void convert() {
        // Given
        RelatedCommodity relatedCommodity = new RelatedCommodity(new RelatedCommodityId(1L), 1L);
        Long commodityId = 1L;

        // When
        RelatedCommodityPO relatedCommodityPO = relatedCommodityDataConverter.convert(relatedCommodity, commodityId);
        RelatedCommodity relatedCommodity1 = relatedCommodityDataConverter.convert(relatedCommodityPO);

        // Then
        assertThat(relatedCommodity).isEqualTo(relatedCommodity1);
    }

}
