package com.ricky.types;

import cn.hutool.core.util.StrUtil;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className RealName
 * @desc 实名
 */
@Value
public class RealName implements ValueObject {

    String firstName;
    String lastName;

    public RealName(String firstName, String lastName) {
        if (isIllegal(firstName, lastName)) {
            throw new IllegalArgumentException("不正确的实名");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private boolean isIllegal(String firstName, String lastName) {
        return StrUtil.isBlank(firstName) && StrUtil.isBlank(lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
