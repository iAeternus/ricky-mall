package com.ricky.assembler;

import com.ricky.domain.user.model.aggregate.User;
import com.ricky.domain.user.model.entity.BusinessUser;
import com.ricky.domain.user.model.entity.EnterpriseUser;
import com.ricky.dto.command.ApplyEnterpriseUserCommand;
import com.ricky.dto.command.ApplyForStoreAuthCommand;
import com.ricky.dto.command.RegisterCommand;
import com.ricky.dto.query.AuthenticationQuery;
import com.ricky.dto.query.EmailQuery;
import com.ricky.dto.response.AuthenticationResponse;
import com.ricky.dto.response.RegisterResponse;
import com.ricky.dto.response.UserInfoResponse;
import com.ricky.enums.impl.PasswordStatus;
import com.ricky.enums.impl.PasswordStrength;
import com.ricky.persistence.converter.decorator.PasswordDecorator;
import com.ricky.types.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/10
 * @className UserAssembler
 * @desc
 */
@Mapper(componentModel = "spring", uses = PasswordDecorator.class)
public interface UserAssembler {

    @Mappings({
            @Mapping(target = "email.address", source = "email"),
            // @Mapping(target = "password.value", source = "password"),
            @Mapping(target = "nickname.value", source = "nickname"),
            @Mapping(target = "realName.firstName", source = "firstName"),
            @Mapping(target = "realName.lastName", source = "lastName"),
            @Mapping(target = "phoneNumber.value", source = "phoneNumber"),
    })
    User convert(RegisterCommand command);

    @Mappings({
            @Mapping(target = "email.address", source = "email"),
            // @Mapping(target = "password", source = "password.value"),
    })
    User convert(AuthenticationQuery query);

    RegisterResponse convert(String token, PasswordStrength strength);

    AuthenticationResponse convert(String token);

    @Mappings({
            @Mapping(target = "userId.value", source = "userId"),
            @Mapping(target = "company.recordNumber", source = "recordNumber"),
            @Mapping(target = "company.name", source = "companyName"),
            @Mapping(target = "company.ceo", source = "ceo"),
    })
    EnterpriseUser convert(ApplyEnterpriseUserCommand command);

    @Mappings({
            @Mapping(target = "address", source = "email"),
    })
    Email convert(EmailQuery query);

    @Mappings({
            @Mapping(target = "id", source = "id.value"),
            @Mapping(target = "email", source = "email.address"),
            @Mapping(target = "nickname", source = "nickname.value"),
            @Mapping(target = "firstName", source = "realName.firstName"),
            @Mapping(target = "lastName", source = "realName.lastName"),
            @Mapping(target = "phoneNumber", source = "phoneNumber.value"),
            @Mapping(target = "integral", source = "integral.value"),
            @Mapping(target = "level", source = "level.value"),
    })
    UserInfoResponse convert(User user);

    @Mappings({
            @Mapping(target = "userId.value", source = "userId"),
            @Mapping(target = "store.name", source = "storeName"),
            @Mapping(target = "store.boss", source = "boss"),
            @Mapping(target = "store.recordNumber", source = "recordNumber"),
    })
    BusinessUser convert(ApplyForStoreAuthCommand command);

    // public User convert(RegisterCommand request) {
    //     User user = new User();
    //     user.setEmail(new Email(request.getEmail()));
    //     user.setPassword(new Password(request.getPassword(), PasswordStatus.UNENCRYPTED));
    //     user.setNickname(new Nickname(request.getNickname()));
    //     user.setRealName(new RealName(request.getFirstName(), request.getLastName()));
    //     user.setPhoneNumber(new PhoneNumber(request.getPhoneNumber()));
    //     return user;
    // }
    //
    // public User convert(AuthenticationQuery request) {
    //     User user = new User();
    //     user.setEmail(new Email(request.getEmail()));
    //     user.setPassword(new Password(request.getPassword(), PasswordStatus.UNENCRYPTED));
    //     return user;
    // }
    //
    // public RegisterResponse convert(String token, PasswordStrength strength) {
    //     return new RegisterResponse(token, strength);
    // }
    //
    // public AuthenticationResponse convert(String token) {
    //     return new AuthenticationResponse(token);
    // }
    //
    // public EnterpriseUser convert(ApplyEnterpriseUserCommand command) {
    //     EnterpriseUser enterpriseUser = new EnterpriseUser();
    //     enterpriseUser.setUserId(new UserId(command.getUserId()));
    //     enterpriseUser.setCompany(new Company(command.getRecordNumber(), command.getName(), command.getCeo()));
    //     return enterpriseUser;
    // }
    //
    // public Email convert(EmailQuery query) {
    //     return new Email(query.getEmail());
    // }
    //
    // public UserInfoResponse convert(User user) {
    //     UserInfoResponse userInfoResponse = new UserInfoResponse();
    //     userInfoResponse.setId(user.getId().getValue());
    //     userInfoResponse.setEmail(user.getEmail().getAddress());
    //     userInfoResponse.setNickname(user.getNickname().getValue());
    //     userInfoResponse.setFirstName(user.getRealName().getFirstName());
    //     userInfoResponse.setLastName(user.getRealName().getLastName());
    //     userInfoResponse.setPhoneNumber(user.getPhoneNumber().getValue());
    //     userInfoResponse.setRole(user.getRole());
    //     userInfoResponse.setIntegral(user.getIntegral().getValue());
    //     userInfoResponse.setLevel(user.getLevel().getValue());
    //     userInfoResponse.setBalance(user.getBalance());
    //     return userInfoResponse;
    // }
    //
    // public BusinessUser convert(ApplyForStoreAuthCommand command) {
    //     BusinessUser businessUser = new BusinessUser();
    //     businessUser.setUserId(new UserId(command.getUserId()));
    //     businessUser.setStore(new Store(
    //             command.getName(),
    //             command.getBoss(),
    //             command.getRecordNumber()
    //     ));
    //     businessUser.setStoreType(command.getStoreType());
    //     return businessUser;
    // }
}
