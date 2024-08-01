package com.ricky.persistence.converter.impl;

import com.ricky.domain.user.model.aggregate.User;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.converter.decorator.MoneyDecorator;
import com.ricky.persistence.converter.decorator.PasswordDecorator;
import com.ricky.persistence.po.UserPO;
import com.ricky.types.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className UserDataConverter
 * @desc
 */
@Mapper(componentModel = "spring", uses = {MoneyDecorator.class, PasswordDecorator.class})
public abstract class UserDataConverter implements DataConverter<User, UserId, UserPO> {

    @Override
    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "email", source = "email.address"),
            // @Mapping(target = "password", source = "password.value"),
            @Mapping(target = "nickname", source = "nickname.value"),
            @Mapping(target = "firstName", source = "realName.firstName"),
            @Mapping(target = "lastName", source = "realName.lastName"),
            @Mapping(target = "phoneNumber", source = "phoneNumber.value"),
            @Mapping(target = "integral", source = "integral.value"),
            @Mapping(target = "level", source = "level.value"),
            @Mapping(target = "balance", source = "balance.amount"),
            @Mapping(target = "currencyCode", source = "balance.currency"),
    })
    public abstract UserPO convert(User entity);

    @Override
    @Mappings({
            @Mapping(target = "id.value", source = "id"),
            @Mapping(target = "email.address", source = "email"),
            // @Mapping(target = "password.value", source = "password"),
            @Mapping(target = "nickname.value", source = "nickname"),
            @Mapping(target = "realName.firstName", source = "firstName"),
            @Mapping(target = "realName.lastName", source = "lastName"),
            @Mapping(target = "phoneNumber.value", source = "phoneNumber"),
            @Mapping(target = "integral.value", source = "integral"),
            @Mapping(target = "level.value", source = "level"),
            @Mapping(target = "balance.amount", source = "balance"),
            @Mapping(target = "balance.currency", source = "currencyCode"),
    })
    public abstract User convert(UserPO po);

    // @Override
    // public UserPO convert(@NonNull User entity) {
    //     UserPO userPO = new UserPO();
    //     UserId userId = entity.getId();
    //     userPO.setId(userId == null ? null : userId.getValue());
    //     userPO.setEmail(entity.getEmail().getAddress());
    //     userPO.setPassword(entity.getPassword().getValue());
    //     userPO.setNickname(entity.getNickname().getValue());
    //     userPO.setFirstName(entity.getRealName().getFirstName());
    //     userPO.setLastName(entity.getRealName().getLastName());
    //     userPO.setPhoneNumber(entity.getPhoneNumber().getValue());
    //     userPO.setRole(entity.getRole());
    //     userPO.setIntegral(entity.getIntegral().getValue());
    //     userPO.setLevel(entity.getLevel().getValue());
    //     userPO.setBalance(entity.getBalance().getAmount());
    //     userPO.setCurrencyCode(entity.getBalance().currencyCode());
    //     return userPO;
    // }
    //
    // @Override
    // public User convert(@NonNull UserPO po) {
    //     User user = new User();
    //     user.setId(new UserId(po.getId()));
    //     user.setEmail(new Email(po.getEmail()));
    //     user.setPassword(new Password(po.getPassword(), false));
    //     user.setNickname(new Nickname(po.getNickname()));
    //     user.setRealName(new RealName(po.getFirstName(), po.getLastName()));
    //     user.setPhoneNumber(new PhoneNumber(po.getPhoneNumber()));
    //     user.setRole(po.getRole());
    //     user.setIntegral(new Integral(po.getIntegral()));
    //     user.setLevel(new Level(po.getLevel()));
    //     user.setBalance(new Money(po.getBalance(), po.getCurrencyCode()));
    //     return user;
    // }

}
