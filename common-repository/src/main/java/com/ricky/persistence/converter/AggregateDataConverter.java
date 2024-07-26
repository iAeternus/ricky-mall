package com.ricky.persistence.converter;

import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.persistence.po.BasePO;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/15
 * @className AggregateDataConverter
 * @desc
 */
public interface AggregateDataConverter<T extends Aggregate<ID>, ID extends Identifier, PO extends BasePO> {

    /**
     * 转换聚合根为持久化对象PO
     *
     * @param aggregate 聚合根
     * @return 持久化对象PO
     */
    PO toPO(@NonNull T aggregate);

    /**
     * 转换持久化对象PO为聚合根
     *
     * @param po 持久化对象PO
     * @return 聚合根
     */
    T toAggregate(@NonNull PO po, Map<Class<?>, List<? extends BasePO>> relatedPOLists);

}
