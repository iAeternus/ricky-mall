package com.ricky.domain.user.model.aggregate;

import com.ricky.enums.UserRole;
import com.ricky.marker.Aggregate;
import com.ricky.types.*;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className User
 * @desc 用户
 */
@Data
public class User implements Aggregate<UserId> {

    private UserId id;
    private Email email;
    private Password password;
    private Nickname nickname;
    private RealName realName;
    private PhoneNumber phoneNumber;
    private UserRole role;

}
