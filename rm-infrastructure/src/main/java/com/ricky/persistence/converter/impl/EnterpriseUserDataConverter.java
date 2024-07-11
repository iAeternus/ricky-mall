package com.ricky.persistence.converter.impl;

import com.ricky.domain.user.model.entity.EnterpriseUser;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.EnterpriseUserPO;
import com.ricky.types.Company;
import com.ricky.types.EnterpriseUserId;
import com.ricky.types.UserId;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className EnterpriseUserDataConverter
 * @desc
 */
@Service
public class EnterpriseUserDataConverter implements DataConverter<EnterpriseUser, EnterpriseUserId, EnterpriseUserPO> {

    @Override
    public EnterpriseUserPO toPO(EnterpriseUser entity) {
        EnterpriseUserPO enterpriseUserPO = new EnterpriseUserPO();
        EnterpriseUserId enterpriseUserId = entity.getId();
        enterpriseUserPO.setId(enterpriseUserId == null ? null : enterpriseUserId.getValue());
        enterpriseUserPO.setUserId(entity.getUserId().getValue());
        enterpriseUserPO.setRecordNumber(entity.getCompany().getRecordNumber());
        enterpriseUserPO.setCompanyName(entity.getCompany().getName());
        enterpriseUserPO.setCeo(entity.getCompany().getCeo());
        return enterpriseUserPO;
    }

    @Override
    public EnterpriseUser toEntity(EnterpriseUserPO po) {
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        enterpriseUser.setId(new EnterpriseUserId(po.getId()));
        enterpriseUser.setUserId(new UserId(po.getUserId()));
        enterpriseUser.setCompany(new Company(po.getRecordNumber(), po.getCompanyName(), po.getCeo()));
        return enterpriseUser;
    }

}
