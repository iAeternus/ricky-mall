package com.ricky.types.user;

import cn.hutool.core.util.StrUtil;
import com.ricky.exception.NullException;
import com.ricky.marker.ValueObject;
import lombok.Value;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/11
 * @className Company
 * @desc 公司
 */
@Value
public class Company implements ValueObject {

    String recordNumber; // 备案号
    String name; // 公司名
    String ceo; // 首席执行官

    public Company(String recordNumber, String name, String ceo) {
        NullException.isNull(recordNumber, "备案号不能为空");
        NullException.isNull(name, "公司名不能为空");
        NullException.isNull(ceo, "ceo不能为空");

        this.recordNumber = recordNumber;
        this.name = name;
        this.ceo = ceo;
    }

}
