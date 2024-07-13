package com.ricky.types.commodity;

import com.ricky.exception.NullException;
import com.ricky.marker.ValueObject;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/13
 * @className SalesInformation
 * @desc 销售信息
 */
@Value
public class SalesInformation implements ValueObject {

    Integer soldCount; // 销售数量
    LocalDateTime createTime; // 创建时间
    LocalDateTime updateTime; // 更新时间

    public SalesInformation(Integer soldCount, LocalDateTime createTime, LocalDateTime updateTime) {
        NullException.isNull(soldCount, "销售数量不能为空");
        NullException.isNull(createTime, "创建时间不能为空");
        NullException.isNull(updateTime, "更新时间不能为空");

        this.soldCount = soldCount;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

}
