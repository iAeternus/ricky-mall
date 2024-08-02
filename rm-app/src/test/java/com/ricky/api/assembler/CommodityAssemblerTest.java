package com.ricky.api.assembler;

import com.ricky.assembler.CommodityAssembler;
import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.domain.commodity.model.entity.Attribute;
import com.ricky.domain.commodity.model.entity.Image;
import com.ricky.domain.commodity.model.entity.RelatedCommodity;
import com.ricky.domain.commodity.model.entity.Supplier;
import com.ricky.dto.command.ModifyCommodityCommand;
import com.ricky.dto.command.SaveCommodityCommand;
import com.ricky.dto.response.GetCommodityByIdResponse;
import com.ricky.enums.impl.CommodityType;
import com.ricky.enums.impl.WeightUnit;
import com.ricky.types.commodity.*;
import com.ricky.types.common.Money;
import com.ricky.types.common.Weight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/2
 * @className CommodityAssemblerTest
 * @desc
 */
@SpringBootTest
public class CommodityAssemblerTest {

    @Autowired
    private CommodityAssembler commodityAssembler;

    private List<Image> images;

    private List<Attribute> attributes;

    private List<Supplier> suppliers;

    private List<RelatedCommodity> relatedCommodities;

    private LocalDateTime now;

    @BeforeEach
    public void setUp() {
        images = List.of(
                new Image(new ImageId(1L), "T1", "https://bilibili.com", 1024L),
                new Image(new ImageId(2L), "T2", "https://bilibili.com", 1024L),
                new Image(new ImageId(3L), "T3", "https://bilibili.com", 1024L),
                new Image(new ImageId(4L), "T4", "https://bilibili.com", 1024L)
        );

        attributes = List.of(
                new Attribute(new AttributeId(1L), "color", "red"),
                new Attribute(new AttributeId(2L), "size", "small")
        );

        suppliers = List.of(
                new Supplier(new SupplierId(1L), "s1", "xxx", "xxx"),
                new Supplier(new SupplierId(2L), "s2", "xxx", "xxx"),
                new Supplier(new SupplierId(3L), "s3", "xxx", "xxx")
        );

        relatedCommodities = List.of(
                new RelatedCommodity(new RelatedCommodityId(1L), 10L),
                new RelatedCommodity(new RelatedCommodityId(2L), 11L),
                new RelatedCommodity(new RelatedCommodityId(3L), 12L),
                new RelatedCommodity(new RelatedCommodityId(4L), 13L),
                new RelatedCommodity(new RelatedCommodityId(5L), 14L)
        );

        now = LocalDateTime.now();
    }

    @Test
    public void convert1() {
        // Given
        SaveCommodityCommand command = SaveCommodityCommand.builder()
                .name("xxx")
                .description("xxx")
                .price(new Money(10000))
                .stock(1000)
                .weight(10.0)
                .weightUnit(WeightUnit.KILOGRAM)
                .categoryId(1L)
                .brandName("xxx")
                .metaTitle("xxx")
                .metaKeywords("xxx")
                .metaDescription("xxxxxx")
                .images(images)
                .attributes(attributes)
                .suppliers(suppliers)
                .relatedCommodities(relatedCommodities)
                .build();

        // When
        Commodity commodity = commodityAssembler.convert(command);

        // Then
        assertThat(commodity).isEqualTo(Commodity.builder()
                .name(new CommodityName("xxx"))
                .description(new ProductDescription("xxx"))
                .price(new Money(10000, "CNY"))
                .stock(new Stock(1000))
                .weight(new Weight(10.0, WeightUnit.KILOGRAM))
                .categoryId(new CategoryId(1L))
                .brand(new Brand("xxx"))
                .seo(new SEO("xxx", "xxx", "xxxxxx"))
                .images(images)
                .attributes(attributes)
                .suppliers(suppliers)
                .relatedCommodities(relatedCommodities)
                .build());
    }

    @Test
    public void convert2() {
        // Given
        ModifyCommodityCommand command = ModifyCommodityCommand.builder()
                .id(1L)
                .name("xxx")
                .description("xxx")
                .price(new Money(10000))
                .weight(10.0)
                .weightUnit(WeightUnit.KILOGRAM)
                .categoryId(1L)
                .brandName("xxx")
                .metaTitle("xxx")
                .metaKeywords("xxx")
                .metaDescription("xxxxxx")
                .images(images)
                .attributes(attributes)
                .suppliers(suppliers)
                .relatedCommodities(relatedCommodities)
                .build();

        // When
        Commodity commodity = commodityAssembler.convert(command);

        // Then
        assertThat(commodity).isEqualTo(Commodity.builder()
                .id(new CommodityId(1L))
                .name(new CommodityName("xxx"))
                .description(new ProductDescription("xxx"))
                .price(new Money(10000, "CNY"))
                .weight(new Weight(10.0, WeightUnit.KILOGRAM))
                .categoryId(new CategoryId(1L))
                .brand(new Brand("xxx"))
                .seo(new SEO("xxx", "xxx", "xxxxxx"))
                .images(images)
                .attributes(attributes)
                .suppliers(suppliers)
                .relatedCommodities(relatedCommodities)
                .build());
    }

    @Test
    public void convert3() {
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
                .images(images)
                .attributes(attributes)
                .suppliers(suppliers)
                .relatedCommodities(relatedCommodities)
                .build();

        // When
        GetCommodityByIdResponse response = commodityAssembler.convert(commodity);

        // Then
        assertThat(response).isEqualTo(GetCommodityByIdResponse.builder()
                .id(1L)
                .name("xxx")
                .description("xxx")
                .price(new Money(10000, "CNY"))
                .stock(1000)
                .weight(10.0)
                .weightUnit(WeightUnit.KILOGRAM)
                .commodityType(CommodityType.LISTED)
                .categoryId(1L)
                .brandName("xxx")
                .discountPrice(new Money(999, "CNY"))
                .promotionStartTime(now)
                .promotionEndTime(now)
                .soldCount(100)
                .createTime(now)
                .updateTime(now)
                .metaTitle("xxx")
                .metaKeywords("xxx")
                .metaDescription("xxxxxx")
                .images(images)
                .attributes(attributes)
                .suppliers(suppliers)
                .relatedCommodities(List.of(10L, 11L, 12L, 13L, 14L))
                .build());
    }

    @Test
    public void convert4() {
        // Given
        Long commodityId = 1L;

        // When
        Commodity commodity = commodityAssembler.convert(commodityId);

        // Then
        assertThat(commodity).isEqualTo(Commodity.builder()
                .id(new CommodityId(1L))
                .build());
    }

}
