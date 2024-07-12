package com.ricky.domain.user.repository;

import com.ricky.domain.user.model.entity.BusinessUser;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/12
 * @className BusinessUserRepository
 * @desc
 */
@Repository
public interface BusinessUserRepository {
    void saveBusinessUser(BusinessUser businessUser);
}
