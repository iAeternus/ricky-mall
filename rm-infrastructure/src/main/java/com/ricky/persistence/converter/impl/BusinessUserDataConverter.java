package com.ricky.persistence.converter.impl;

import com.ricky.domain.user.model.entity.BusinessUser;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.BusinessUserPO;
import com.ricky.types.BusinessUserId;
import com.ricky.types.Store;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className BusinessUserDataConverter
 * @desc
 */
@Service
public class BusinessUserDataConverter implements DataConverter<BusinessUser, BusinessUserId, BusinessUserPO> {
    @Override
    public BusinessUserPO toPO(BusinessUser entity) {
        BusinessUserPO businessUserPO = new BusinessUserPO();
        BusinessUserId id = entity.getId();
        businessUserPO.setId(id == null ? null : id.getValue());
        businessUserPO.setUserId(entity.getUserId().getValue());
        businessUserPO.setStoreName(entity.getStore().getName());
        businessUserPO.setBoss(entity.getStore().getBoss());
        businessUserPO.setRecordNumber(entity.getStore().getRecordNumber());
        businessUserPO.setStoreType(entity.getStoreType());
        return businessUserPO;
    }

    @Override
    public BusinessUser toEntity(BusinessUserPO po) {
        BusinessUser businessUser = new BusinessUser();
        businessUser.setId(new BusinessUserId(po.getId()));
        businessUser.setStore(new Store(
                po.getStoreName(),
                po.getBoss(),
                po.getRecordNumber()
        ));
        businessUser.setStoreType(po.getStoreType());
        return businessUser;
    }
}
