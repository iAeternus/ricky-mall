package com.ricky.persistence.converter.impl;

import com.ricky.domain.user.model.User;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.po.UserPO;
import com.ricky.types.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className UserDataConverter
 * @desc
 */
@Service
@RequiredArgsConstructor
public class UserDataConverter implements DataConverter<User, UserId, UserPO> {

    @Override
    public UserPO toPO(User entity) {
        UserPO userPO = new UserPO();
        UserId userId = entity.getId();
        userPO.setId(userId == null ? null : userId.getValue());
        userPO.setEmail(entity.getEmail().getAddress());
        userPO.setPassword(entity.getPassword().getValue());
        userPO.setNickname(entity.getNickname().getValue());
        userPO.setFirstName(entity.getRealName().getFirstName());
        userPO.setLastName(entity.getRealName().getLastName());
        userPO.setPhoneNumber(entity.getPhoneNumber().getValue());
        userPO.setRole(entity.getRole());
        return userPO;
    }

    @Override
    public User toEntity(UserPO po) {
        User user = new User();
        user.setId(new UserId(po.getId()));
        user.setEmail(new Email(po.getEmail()));
        user.setPassword(new Password(po.getPassword(), false));
        user.setNickname(new Nickname(po.getNickname()));
        user.setRealName(new RealName(po.getFirstName(), po.getLastName()));
        user.setPhoneNumber(new PhoneNumber(po.getPhoneNumber()));
        user.setRole(po.getRole());
        return user;
    }
}
