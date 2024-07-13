package com.ricky.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className AuthenticationResponse
 * @desc
 */
@Data
@AllArgsConstructor
public class AuthenticationResponse implements Response {

    private String token;

}
