package com.ricky.marker;

import java.io.Serializable;

/**
 * @author Ricky
 * @version 2.0
 * @date 2024/7/15
 * @className Identifier
 * @desc ID类型DP的Marker接口
 * 我们约定，ID类型内部字段为Long
 */
public interface Identifier extends Serializable {

    /**
     * 获取ID的值
     * @return ID的值
     */
    Long getValue();

}