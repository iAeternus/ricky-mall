package com.ricky.dto.response;

import com.ricky.enums.impl.UserRole;
import com.ricky.types.common.Money;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className UserInfoResponse
 * @desc
 */
@Data
public class UserInfoResponse implements Response {

    private Long id;
    private String email;
    private String nickname;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserRole role;
    private Long integral;
    private Integer level;
    private Money balance;

}
