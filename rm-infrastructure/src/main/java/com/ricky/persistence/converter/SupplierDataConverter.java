package com.ricky.persistence.converter;

import com.ricky.domain.commodity.model.entity.Supplier;
import com.ricky.persistence.converter.AssociationDataConverter;
import com.ricky.persistence.converter.decorator.ForeignKeyDecorator;
import com.ricky.persistence.po.SupplierPO;
import com.ricky.types.commodity.SupplierId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.io.Serializable;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/29
 * @className SupplierDataConverter
 * @desc
 */
@Mapper(componentModel = "spring", uses = ForeignKeyDecorator.class)
public abstract class SupplierDataConverter implements AssociationDataConverter<Supplier, SupplierId, SupplierPO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "entity.id.value"),
            @Mapping(target = "commodityId", source = "foreignKey"),
    })
    public abstract SupplierPO convert(Supplier entity, Serializable foreignKey);

    @Override
    @Mappings({
            @Mapping(target = "id", source = "entity.id.value"),
    })
    public abstract SupplierPO convert(Supplier entity);

    @Override
    @Mappings({
            @Mapping(target = "id.value", source = "id"),
    })
    public abstract Supplier convert(SupplierPO po);

    // @Override
    // public SupplierPO convert(@NonNull Supplier entity) {
    //     return SupplierPO.builder()
    //             .name(entity.getName())
    //             .contact(entity.getContact())
    //             .address(entity.getAddress())
    //             .build();
    // }
    //
    // @Override
    // public Supplier convert(@NonNull SupplierPO po) {
    //     return Supplier.builder()
    //             .id(new SupplierId(po.getId()))
    //             .name(po.getName())
    //             .contact(po.getContact())
    //             .address(po.getAddress())
    //             .build();
    // }
    //
    // @Override
    // public SupplierPO convert(@NonNull Supplier entity, Serializable foreignKey) {
    //     SupplierPO po = convert(entity);
    //     po.setCommodityId((Long) foreignKey);
    //     return po;
    // }
}
