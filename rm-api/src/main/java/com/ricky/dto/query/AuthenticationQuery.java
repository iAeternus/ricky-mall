package com.ricky.dto.query;

import lombok.Data;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className AuthenticationQuery
 * @desc
 */
@Data
public class AuthenticationQuery implements Query {

    private String email;
    private String password;

}
