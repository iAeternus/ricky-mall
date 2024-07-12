package com.ricky.domain.user.service;

import com.ricky.domain.user.model.aggregate.User;
import com.ricky.domain.user.model.entity.BusinessUser;
import com.ricky.domain.user.model.entity.EnterpriseUser;
import com.ricky.enums.UserRole;
import com.ricky.types.Email;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className UserDomainServiceImpl
 * @desc
 */
@Service
public interface UserDomainService {
    void saveUser(User user);

    Map<String, Object> getClaims(User user);

    User login(User user);

    User getById(Long userId);

    void changeRole(User user, UserRole userRole);

    void saveEnterpriseUser(EnterpriseUser enterpriseUser);

    User getByEmail(Email email);

    void updateUserById(User user);

    void saveBusinessUser(BusinessUser businessUser);

}
