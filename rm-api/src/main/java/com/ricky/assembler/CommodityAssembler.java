package com.ricky.assembler;

import com.ricky.domain.commodity.model.aggregate.Commodity;
import com.ricky.dto.command.SaveCommodityCommand;
import com.ricky.types.commodity.*;
import com.ricky.types.common.Money;
import com.ricky.types.common.Weight;
import org.springframework.stereotype.Service;

import java.net.CookieManager;
import java.util.Currency;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className CommodityAssembler
 * @desc
 */
@Service
public class CommodityAssembler {

    public Commodity toCommodity(SaveCommodityCommand command) {
        return Commodity.builder()
                .id(new CommodityId(command.getId()))
                .name(new CommodityName(command.getName()))
                .description(new ProductDescription(command.getDescription()))
                .price(command.getPrice())
                .stock(new Stock(command.getStock()))
                .type(command.getCommodityType())
                .pictureInformation(new PictureInformation(command.getMainImageUrl(), null))
                .categoryId(new CategoryId(command.getCategoryId()))
                .brand(new Brand(command.getBrand()))
                .shippingInformation(new ShippingInformation(new Weight(command.getWeight(), command.getWeightUnit())))
                .supplierInformation(new SupplierInformation(command.getSupplierId()))
                .seo(new SEO(
                        command.getMetaTitle(),
                        command.getMetaKeywords(),
                        command.getMetaDescription()
                ))
                .build();
    }

}
