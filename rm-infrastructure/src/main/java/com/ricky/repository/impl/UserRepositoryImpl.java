package com.ricky.repository.impl;

import com.ricky.domain.user.model.User;
import com.ricky.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className UserRepositoryImpl
 * @desc
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {



    @Override
    public void saveUser(User user) {

    }
}
