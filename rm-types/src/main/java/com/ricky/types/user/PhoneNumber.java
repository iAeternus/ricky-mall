package com.ricky.types.user;

import cn.hutool.core.util.StrUtil;
import com.ricky.exception.NullException;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className PhoneNumber
 * @desc 电话号码
 */
@Value
public class PhoneNumber implements ValueObject {

    String value;

    public PhoneNumber(String value) {
        NullException.isNull(value, "phone number不能为空");
        if (isIllegal(value)) {
            throw new IllegalArgumentException("不正确的电话号码");
        }
        this.value = value;
    }

    private boolean isIllegal(String phoneNumber) {
        final String phoneRegex = "^1[3-9]\\d{9}$\n";
        return phoneNumber.matches(phoneRegex);
    }

}
