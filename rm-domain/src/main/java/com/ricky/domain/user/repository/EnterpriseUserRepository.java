package com.ricky.domain.user.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ricky.domain.user.model.entity.EnterpriseUser;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className EnterpriseUserRepository
 * @desc
 */
@Repository
public interface EnterpriseUserRepository {
    void saveEnterpriseUser(EnterpriseUser enterpriseUser);

}
