package com.ricky.persistence.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ricky.enums.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className UserPO
 * @desc
 */
@Data
@TableName("user")
@EqualsAndHashCode(callSuper = true)
public class UserPO extends BasePO {

    @TableId
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private UserRole role;

}
