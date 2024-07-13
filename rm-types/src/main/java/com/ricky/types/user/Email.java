package com.ricky.types.user;

import cn.hutool.core.util.StrUtil;
import com.ricky.exception.NullException;
import com.ricky.marker.ValueObject;
import lombok.Value;

import java.util.Objects;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className Email
 * @desc 邮箱
 */
@Value
public class Email implements ValueObject {

    String address;

    public Email(String address) {
        NullException.isNull(address, "邮箱地址不能为空");
        if (isIllegal(address)) {
            throw new IllegalArgumentException("不合法的邮箱地址");
        }
        this.address = address;
    }

    private boolean isIllegal(String address) {
        final String emailRegex = "^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$\n";
        return address.matches(emailRegex);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Email email = (Email) obj;
        return address.equals(email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "Email{" +
                "address='" + address + '\'' +
                '}';
    }

}
