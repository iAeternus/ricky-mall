package com.ricky.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ricky.domain.user.model.entity.BusinessUser;
import com.ricky.domain.user.repository.BusinessUserRepository;
import com.ricky.persistence.converter.BusinessUserDataConverter;
import com.ricky.persistence.mapper.BusinessUserMapper;
import com.ricky.persistence.po.BusinessUserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className BusinessUserRepositoryImpl
 * @desc
 */
@Repository
@RequiredArgsConstructor
public class BusinessUserRepositoryImpl extends ServiceImpl<BusinessUserMapper, BusinessUserPO> implements BusinessUserRepository {

    private BusinessUserDataConverter businessUserDataConverter;

    @Override
    public void saveBusinessUser(BusinessUser businessUser) {
        BusinessUserPO businessUserPO = businessUserDataConverter.convert(businessUser);
        save(businessUserPO);
    }
}
