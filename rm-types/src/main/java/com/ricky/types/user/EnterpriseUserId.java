package com.ricky.types.user;

import com.ricky.exception.NullException;
import com.ricky.marker.Identifier;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className EnterpriseUserId
 * @desc
 */
@Value
public class EnterpriseUserId implements Identifier {

    Long value;

    public EnterpriseUserId(Long value) {
        NullException.isNull(value, "企业用户id不能为空");
        this.value = value;
    }
}
