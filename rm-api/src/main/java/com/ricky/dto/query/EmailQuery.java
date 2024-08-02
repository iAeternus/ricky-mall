package com.ricky.dto.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className EmailQuery
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailQuery implements Query {

    private String email;

}
