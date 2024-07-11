package com.ricky.domain.user.service.impl;

import com.ricky.constants.JwtClaimsConstant;
import com.ricky.constants.MessageConstant;
import com.ricky.domain.user.model.aggregate.User;
import com.ricky.domain.user.model.entity.EnterpriseUser;
import com.ricky.domain.user.repository.EnterpriseUserRepository;
import com.ricky.domain.user.repository.UserRepository;
import com.ricky.domain.user.service.UserDomainService;
import com.ricky.enums.UserRole;
import com.ricky.exception.ForbiddenException;
import com.ricky.exception.NotFoundException;
import com.ricky.types.Email;
import com.ricky.types.Password;
import com.ricky.types.UserId;
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
    private final EnterpriseUserRepository enterpriseUserRepository;

    @Override
    public void saveUser(User user) {
        user.toEnterpriseUser();
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

    @Override
    public User getById(Long userId) {
        User user = userRepository.getUserById(userId);
        if(user == null) {
            throw new NotFoundException(MessageConstant.USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    public void changeRole(User user, UserRole userRole) {
        user.setRole(userRole);
        userRepository.updateUserById(user);
    }

    @Override
    public void saveEnterpriseUser(EnterpriseUser enterpriseUser) {
        enterpriseUserRepository.saveEnterpriseUser(enterpriseUser);
    }

    @Override
    public User getByEmail(Email email) {
        return userRepository.getByEmail(email);
    }

}
