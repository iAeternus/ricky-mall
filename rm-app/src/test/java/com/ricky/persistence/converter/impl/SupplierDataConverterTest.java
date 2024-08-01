package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.entity.Supplier;
import com.ricky.persistence.po.SupplierPO;
import com.ricky.types.commodity.SupplierId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className SupplierDataConverterTest
 * @desc
 */
@SpringBootTest
public class SupplierDataConverterTest {

    @Autowired
    private SupplierDataConverter supplierDataConverter;

    @Test
    public void convert() {
        // Given
        Supplier supplier = new Supplier(new SupplierId(1L), "xxx", "xxx", "xxx");
        Long commodityId = 1L;

        // When
        SupplierPO supplierPO = supplierDataConverter.convert(supplier, commodityId);
        Supplier supplier1 = supplierDataConverter.convert(supplierPO);

        // Then
        assertThat(supplier).isEqualTo(supplier1);
    }

}
