package com.ricky.persistence.converter.impl;

import com.ricky.domain.user.model.entity.BusinessUser;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.BusinessUserPO;
import com.ricky.types.user.BusinessUserId;
import com.ricky.types.user.Store;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className BusinessUserDataConverter
 * @desc
 */
@Mapper(componentModel = "spring")
public abstract class BusinessUserDataConverter implements DataConverter<BusinessUser, BusinessUserId, BusinessUserPO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "userId", source = "userId.value"),
            @Mapping(target = "storeName", source = "store.name"),
            @Mapping(target = "boss", source = "store.boss"),
            @Mapping(target = "recordNumber", source = "store.recordNumber"),
    })
    public abstract BusinessUserPO convert(BusinessUser entity);

    @Override
    @Mappings({
            @Mapping(target = "id.value", source = "id"),
            @Mapping(target = "userId.value", source = "userId"),
            @Mapping(target = "store.name", source = "storeName"),
            @Mapping(target = "store.boss", source = "boss"),
            @Mapping(target = "store.recordNumber", source = "recordNumber"),
    })
    public abstract BusinessUser convert(BusinessUserPO po);

}
