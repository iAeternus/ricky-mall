package com.ricky.dto.response;

import com.ricky.enums.impl.PasswordStrength;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className RegisterResponse
 * @desc
 */
@Data
@AllArgsConstructor
public class RegisterResponse implements Response {

    private String token;
    private PasswordStrength strength;

}
