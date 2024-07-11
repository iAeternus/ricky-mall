package com.ricky.domain.user.service.impl;

import com.ricky.constants.JwtClaimsConstant;
import com.ricky.constants.MessageConstant;
import com.ricky.domain.user.model.User;
import com.ricky.domain.user.repository.UserRepository;
import com.ricky.domain.user.service.UserDomainService;
import com.ricky.enums.UserRole;
import com.ricky.exception.ForbiddenException;
import com.ricky.exception.NotFoundException;
import com.ricky.types.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        user.setRole(UserRole.ORDINARY_USERS);
        userRepository.saveUser(user);
    }

    @Override
    public Map<String, Object> getClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId().getValue());
        claims.put(JwtClaimsConstant.EMAIL, user.getEmail().getAddress());
        claims.put(JwtClaimsConstant.PHONE_NUMBER, user.getPhoneNumber().getValue());
        return claims;
    }

    @Override
    public User login(User user) {
        User userFromDataBase = userRepository.getByEmail(user.getEmail());
        if (userFromDataBase == null) {
            throw new NotFoundException(MessageConstant.USER_NOT_FOUND);
        }
        if (!Password.checkPassword(user.getPassword(), userFromDataBase.getPassword())) {
            throw new ForbiddenException(MessageConstant.INCORRECT_PASSWORD);
        }
        return userFromDataBase;
    }

}
