package com.ricky.enums.impl;

import com.ricky.enums.BaseEnum;
import lombok.Getter;
import org.w3c.dom.css.CSSStyleSheet;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/1
 * @className PasswordStatus
 * @desc
 */
@Getter
public enum PasswordStatus implements BaseEnum {

    UNENCRYPTED((short) 0, "未加密"),
    ENCRYPTED((short) 1, "已加密"),
    ;

    final Short code;
    final String description;

    PasswordStatus(Short code, String description) {
        this.code = code;
        this.description = description;
    }

}
