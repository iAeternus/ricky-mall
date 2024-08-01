package com.ricky.marker;

import java.io.Serializable;

/**
 * @author Ricky
 * @version 2.0
 * @date 2024/7/15
 * @className Entity
 * @desc 实体类的Marker接口，标记一般实体以及聚合根关联对象
 */
public interface Entity<ID extends Identifier> extends Identifiable<ID>, Serializable {


}