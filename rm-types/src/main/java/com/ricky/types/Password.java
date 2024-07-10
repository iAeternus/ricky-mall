package com.ricky.types;

import cn.hutool.core.util.StrUtil;
import org.springframework.util.DigestUtils;
import com.ricky.enums.PasswordStrength;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className Password
 * @desc 密码
 */
@Value
public class Password implements ValueObject {

    String beforeEncryption; // 原始密码
    String afterEncryption; // 加密后的密码
    PasswordStrength strength; // 密码强度

    public Password(String password) {
        if (StrUtil.isBlank(password)) {
            throw new IllegalArgumentException("password不能为空");
        }
        this.strength = calculateStrength(password);
        this.beforeEncryption = password;
        this.afterEncryption = DigestUtils.md5DigestAsHex(password.getBytes()); // TODO
    }

    /**
     * 校验密码
     * @param password 密码
     * @return 密码正确返回true，否则返回false
     */
    public boolean checkPassword(String password) {
        return password.equals(this.afterEncryption);
    }

    public String getValue() {
        return this.afterEncryption;
    }

    // 检查密码是否包含特定类型的字符
    private boolean containsUpperCase(String password) {
        return password.matches(".*[A-Z].*");
    }

    private boolean containsLowerCase(String password) {
        return password.matches(".*[a-z].*");
    }

    private boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private boolean containsSpecialChar(String password) {
        String specialChars = "!@#$%^&*()_+{}:\"<>?";
        for (int i = 0; i < specialChars.length(); i++) {
            if (password.indexOf(specialChars.charAt(i)) >= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 计算密码强度
     *
     * @return 返回密码强度
     */
    private PasswordStrength calculateStrength(String password) {
        int lengthScore = Math.min(password.length(), 10); // 密码长度加分，最多加10分
        int upperCaseScore = containsUpperCase(password) ? 2 : 0; // 大写字母加2分
        int lowerCaseScore = containsLowerCase(password) ? 2 : 0; // 小写字母加2分
        int digitScore = containsDigit(password) ? 2 : 0; // 数字加2分
        int specialCharScore = containsSpecialChar(password) ? 3 : 0; // 特殊字符加3分

        int totalScore = lengthScore + upperCaseScore + lowerCaseScore + digitScore + specialCharScore;

        if (totalScore < 6) {
            return PasswordStrength.VERY_WEAK;
        } else if (totalScore < 12) {
            return PasswordStrength.WEAK;
        } else if (totalScore < 18) {
            return PasswordStrength.MEDIUM;
        } else {
            return PasswordStrength.STRONG;
        }
    }

}
