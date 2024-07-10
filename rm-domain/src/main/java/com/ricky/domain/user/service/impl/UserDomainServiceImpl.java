package com.ricky.domain.user.service.impl;

import com.ricky.domain.user.model.User;
import com.ricky.domain.user.repository.UserRepository;
import com.ricky.domain.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className UserDomainServiceImpl
 * @desc
 */
@Service
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

}
