package com.ricky.types.common;

import com.ricky.marker.ValueObject;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/12
 * @className DateTimeInfo
 * @desc
 */
@Value
public class DateTimeInfo implements ValueObject {

    /**
     * 创建时间
     */
    LocalDateTime createTime;

    /**
     * 修改时间
     */
    LocalDateTime updateTime;

}
