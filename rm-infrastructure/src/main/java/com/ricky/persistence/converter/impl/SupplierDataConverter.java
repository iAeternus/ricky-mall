package com.ricky.persistence.converter.impl;

import com.ricky.domain.commodity.model.entity.Supplier;
import com.ricky.marker.Entity;
import com.ricky.persistence.converter.AssociationDataConverter;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.BasePO;
import com.ricky.persistence.po.SupplierPO;
import com.ricky.types.commodity.SupplierId;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/29
 * @className SupplierDataConverter
 * @desc
 */
@Service
public class SupplierDataConverter implements AssociationDataConverter<Supplier, SupplierId, SupplierPO> {

    @Override
    public SupplierPO toPO(@NonNull Supplier entity) {
        return SupplierPO.builder()
                .name(entity.getName())
                .contact(entity.getContact())
                .address(entity.getAddress())
                .build();
    }

    @Override
    public Supplier toEntity(@NonNull SupplierPO po) {
        return Supplier.builder()
                .id(new SupplierId(po.getId()))
                .name(po.getName())
                .contact(po.getContact())
                .address(po.getAddress())
                .build();
    }

    @Override
    public SupplierPO convert(@NonNull Supplier entity, Serializable foreignKey) {
        SupplierPO po = toPO(entity);
        po.setCommodityId((Long) foreignKey);
        return po;
    }
}
