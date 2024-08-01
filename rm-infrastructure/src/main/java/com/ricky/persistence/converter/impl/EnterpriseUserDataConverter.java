package com.ricky.persistence.converter.impl;

import com.ricky.domain.user.model.entity.EnterpriseUser;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.EnterpriseUserPO;
import com.ricky.types.user.Company;
import com.ricky.types.user.EnterpriseUserId;
import com.ricky.types.user.UserId;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className EnterpriseUserDataConverter
 * @desc
 */
@Mapper(componentModel = "spring")
public abstract class EnterpriseUserDataConverter implements DataConverter<EnterpriseUser, EnterpriseUserId, EnterpriseUserPO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "userId", source = "userId.value"),
            @Mapping(target = "recordNumber", source = "company.recordNumber"),
            @Mapping(target = "companyName", source = "company.name"),
            @Mapping(target = "ceo", source = "company.ceo"),
    })
    public abstract EnterpriseUserPO convert(EnterpriseUser entity);

    @Override
    @Mappings({
            @Mapping(target = "id.value", source = "id"),
            @Mapping(target = "userId.value", source = "userId"),
            @Mapping(target = "company.recordNumber", source = "recordNumber"),
            @Mapping(target = "company.name", source = "companyName"),
            @Mapping(target = "company.ceo", source = "ceo"),
    })
    public abstract EnterpriseUser convert(EnterpriseUserPO po);

}
