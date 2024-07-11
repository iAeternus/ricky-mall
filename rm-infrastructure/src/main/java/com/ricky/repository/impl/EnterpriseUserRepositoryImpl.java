package com.ricky.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ricky.domain.user.model.entity.EnterpriseUser;
import com.ricky.domain.user.repository.EnterpriseUserRepository;
import com.ricky.persistence.converter.impl.EnterpriseUserDataConverter;
import com.ricky.persistence.mapper.EnterpriseUserMapper;
import com.ricky.persistence.po.EnterpriseUserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className EnterpriseUserRepositoryImpl
 * @desc
 */
@Repository
@RequiredArgsConstructor
public class EnterpriseUserRepositoryImpl extends ServiceImpl<EnterpriseUserMapper, EnterpriseUserPO> implements EnterpriseUserRepository {

    private final EnterpriseUserDataConverter enterpriseUserDataConverter;

    @Override
    public void saveEnterpriseUser(EnterpriseUser enterpriseUser) {
        EnterpriseUserPO enterpriseUserPO = enterpriseUserDataConverter.toPO(enterpriseUser);
        save(enterpriseUserPO);
    }
}
