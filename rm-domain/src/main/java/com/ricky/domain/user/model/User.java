package com.ricky.domain.user.model;

import com.ricky.enums.UserRole;
import com.ricky.marker.Aggregate;
import com.ricky.types.*;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className User
 * @desc
 */
@Data
public class User implements Aggregate<UserId> {

    private UserId id;
    private Username username;
    private Password password;
    private RealName realName;
    private Email email;
    private PhoneNumber phoneNumber;
    private UserRole role;

}
