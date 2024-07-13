package com.ricky.types.user;

import com.ricky.exception.NullException;
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
        NullException.isNull(firstName, "姓不能为空");
        NullException.isNull(lastName, "名不能为空");

        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
