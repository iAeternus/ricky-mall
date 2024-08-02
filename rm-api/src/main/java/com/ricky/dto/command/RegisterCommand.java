package com.ricky.dto.command;

import lombok.Builder;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className RegisterCommand
 * @desc 注册请求实体
 */
@Data
@Builder
public class RegisterCommand implements Command {

    private String email;
    private String password;
    private String nickname;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
