package com.ricky.domain.user.service;

import com.ricky.domain.user.model.User;
import org.springframework.stereotype.Service;

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
}