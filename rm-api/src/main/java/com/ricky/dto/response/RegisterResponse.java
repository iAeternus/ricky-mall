package com.ricky.dto.response;

import com.ricky.enums.PasswordStrength;
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
public class RegisterResponse {

    private String token;
    private PasswordStrength strength;

}
