package com.ricky.persistence.po;

import cn.hutool.core.math.Money;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ricky.enums.UserRole;
import com.ricky.types.Integral;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className UserPO
 * @desc
 */
@Data
@TableName(value = "user", autoResultMap = true)
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
    private Long integral;
    private Integer level;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Money balance;

}
