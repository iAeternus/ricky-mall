package com.ricky.repository.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ricky.domain.user.model.User;
import com.ricky.domain.user.repository.UserRepository;
import com.ricky.persistence.converter.impl.UserDataConverter;
import com.ricky.persistence.mapper.UserMapper;
import com.ricky.persistence.po.UserPO;
import com.ricky.types.Email;
import com.ricky.types.UserId;
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
public class UserRepositoryImpl extends ServiceImpl<UserMapper, UserPO> implements UserRepository {

    private final UserDataConverter userDataConverter;

    @Override
    public void saveUser(User user) {
        UserPO userPO = userDataConverter.toPO(user);
        save(userPO);
        user.setId(new UserId(userPO.getId()));
    }

    @Override
    public User getByEmail(Email email) {
        UserPO userPO = lambdaQuery().eq(UserPO::getEmail, email.getAddress()).one();
        return userDataConverter.toEntity(userPO);
    }

}
