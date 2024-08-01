package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.entity.Image;
import com.ricky.persistence.po.CommodityImagePO;
import com.ricky.types.commodity.ImageId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className CommodityImageDataConverterTest
 * @desc
 */
@SpringBootTest
public class CommodityImageDataConverterTest {

    @Autowired
    private CommodityImageDataConverter commodityImageDataConverter;

    @Test
    public void convert() {
        // Given
        Image image = new Image(new ImageId(1L), "xxx", "https://bilibili.com", 1024L);
        Long commodityId = 1L;

        // When
        CommodityImagePO commodityImagePO = commodityImageDataConverter.convert(image, commodityId);
        Image image1 = commodityImageDataConverter.convert(commodityImagePO);

        // Then
        assertThat(image).isEqualTo(image1);
    }

}
