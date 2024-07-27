package com.ricky.marker;

import java.io.Serializable;

/**
 * @author Ricky
 * @version 2.0
 * @date 2024/7/15
 * @className Aggregate
 * @desc 聚合根的Marker接口
 */
public interface Aggregate<ID extends Identifier> extends Entity<ID> {

}