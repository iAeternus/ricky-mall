package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.domain.commodity.model.entity.Attribute;
import com.ricky.domain.commodity.model.entity.Image;
import com.ricky.domain.commodity.model.entity.RelatedCommodity;
import com.ricky.domain.commodity.model.entity.Supplier;
import com.ricky.enums.impl.CommodityType;
import com.ricky.enums.impl.WeightUnit;
import com.ricky.persistence.converter.CommodityDataConverter;
import com.ricky.persistence.po.*;
import com.ricky.types.commodity.*;
import com.ricky.types.common.Money;
import com.ricky.types.common.Weight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className CommodityDataConverterTest
 * @desc
 */
@SpringBootTest
public class CommodityDataConverterTest {

    @Autowired
    private CommodityDataConverter commodityDataConverter;

    private LocalDateTime now;

    @BeforeEach
    public void setUp() {
        now = LocalDateTime.now();
    }

    @Test
    public void convert1() {
        // Given
        Commodity commodity = Commodity.builder()
                .id(new CommodityId(1L))
                .name(new CommodityName("xxx"))
                .description(new ProductDescription("xxx"))
                .price(new Money(10000, "CNY"))
                .stock(new Stock(1000))
                .weight(new Weight(10.0, WeightUnit.KILOGRAM))
                .type(CommodityType.LISTED)
                .categoryId(new CategoryId(1L))
                .brand(new Brand("xxx"))
                .promotion(new Promotion(new Money(999, "CNY"), now, now))
                .salesInformation(new SalesInformation(100, now, now))
                .seo(new SEO("xxx", "xxx", "xxxxxx"))
                .images(List.of(
                        new Image(new ImageId(1L), "T1", "https://bilibili.com", 1024L),
                        new Image(new ImageId(2L), "T2", "https://bilibili.com", 1024L),
                        new Image(new ImageId(3L), "T3", "https://bilibili.com", 1024L),
                        new Image(new ImageId(4L), "T4", "https://bilibili.com", 1024L)
                ))
                .attributes(List.of(
                        new Attribute(new AttributeId(1L), "color", "red"),
                        new Attribute(new AttributeId(2L), "size", "small")
                ))
                .suppliers(List.of(
                        new Supplier(new SupplierId(1L), "s1", "xxx", "xxx"),
                        new Supplier(new SupplierId(2L), "s2", "xxx", "xxx"),
                        new Supplier(new SupplierId(3L), "s3", "xxx", "xxx")
                ))
                .relatedCommodities(List.of(
                        new RelatedCommodity(new RelatedCommodityId(1L), 10L),
                        new RelatedCommodity(new RelatedCommodityId(2L), 11L),
                        new RelatedCommodity(new RelatedCommodityId(3L), 12L),
                        new RelatedCommodity(new RelatedCommodityId(4L), 13L),
                        new RelatedCommodity(new RelatedCommodityId(5L), 14L)
                ))
                .build();

        Map<String, List<BasePO>> associationLists = new HashMap<>();
        associationLists.put(Commodity.RELATED_IMAGES, List.of(
                new CommodityImagePO(1L, 1L, "T1", "https://bilibili.com", 1024L),
                new CommodityImagePO(2L, 1L, "T2", "https://bilibili.com", 1024L),
                new CommodityImagePO(3L, 1L, "T3", "https://bilibili.com", 1024L),
                new CommodityImagePO(4L, 1L, "T4", "https://bilibili.com", 1024L)
        ));
        associationLists.put(Commodity.RELATED_ATTRIBUTES, List.of(
                new AttributePO(1L, 1L, "color", "red"),
                new AttributePO(2L, 1L, "size", "small")
        ));
        associationLists.put(Commodity.RELATED_SUPPLIERS, List.of(
                new SupplierPO(1L, 1L, "s1", "xxx", "xxx"),
                new SupplierPO(2L, 1L, "s2", "xxx", "xxx"),
                new SupplierPO(3L, 1L, "s3", "xxx", "xxx")
        ));
        associationLists.put(Commodity.RELATED_COMMODITIES, List.of(
                new RelatedCommodityPO(1L, 1L, 10L),
                new RelatedCommodityPO(2L, 1L, 11L),
                new RelatedCommodityPO(3L, 1L, 12L),
                new RelatedCommodityPO(4L, 1L, 13L),
                new RelatedCommodityPO(5L, 1L, 14L)
        ));

        // When
        CommodityPO commodityPO = commodityDataConverter.convert(commodity);
        Commodity commodity1 = commodityDataConverter.convert(commodityPO, associationLists);

        // Then
        assertThat(commodity).isEqualTo(commodity1);
    }

}