package com.ricky.types;

import cn.hutool.core.util.StrUtil;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className Company
 * @desc 公司
 */
@Value
public class Company {

    String recordNumber; // 备案号
    String name; // 公司名
    String ceo; // 首席执行官

    public Company(String recordNumber, String name, String ceo) {
        if (StrUtil.isBlank(recordNumber)) {
            throw new IllegalArgumentException("备案号不能为空");
        }
        if (StrUtil.isBlank(name)) {
            throw new IllegalArgumentException("公司名不能为空");
        }
        if (StrUtil.isBlank(ceo)) {
            throw new IllegalArgumentException("ceo不能为空");
        }
        this.recordNumber = recordNumber;
        this.name = name;
        this.ceo = ceo;
    }

}
