package com.ricky.assembler;

import com.ricky.assembler.decorator.PasswordEncryptDecorator;
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
import com.ricky.enums.impl.PasswordStrength;
import com.ricky.types.user.Email;
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
@Mapper(componentModel = "spring", uses = PasswordEncryptDecorator.class)
public interface UserAssembler {

    @Mappings({
            @Mapping(target = "email.address", source = "email"),
            @Mapping(target = "nickname.value", source = "nickname"),
            @Mapping(target = "realName.firstName", source = "firstName"),
            @Mapping(target = "realName.lastName", source = "lastName"),
            @Mapping(target = "phoneNumber.value", source = "phoneNumber"),
    })
    User convert(RegisterCommand command);

    @Mappings({
            @Mapping(target = "email.address", source = "email"),
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

}
