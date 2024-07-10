package com.ricky.domain.user.repository;

import com.ricky.domain.user.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className UserRepository
 * @desc
 */
@Repository
public interface UserRepository {
    void saveUser(User user);
}
