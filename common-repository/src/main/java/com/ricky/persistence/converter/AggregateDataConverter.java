package com.ricky.persistence.converter;

import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.persistence.po.BasePO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/15
 * @className AggregateDataConverter
 * @desc
 */
@Service
public interface AggregateDataConverter<T extends Aggregate<ID>, ID extends Identifier, PO extends BasePO> {

    /**
     * 转换聚合根为持久化对象PO
     *
     * @param aggregate 聚合根
     * @return 持久化对象PO
     */
    PO convert(T aggregate);

    /**
     * 转换持久化对象PO为聚合根
     *
     * @param po 持久化对象PO
     * @return 聚合根
     */
    <P extends BasePO> T convert(PO po, Map<String, List<P>> relatedPOLists);

    /**
     * 获取关联对象PO列表
     * @param aggregate 聚合根
     * @return 返货Map，键-字段名，值-关联对象PO列表
     * @param <P> 关联对象PO类型
     */
    <P extends BasePO> Map<String, List<P>> getAssociationPOLists(@NonNull T aggregate);

    /**
     * 设置聚合根ID
     *
     * @param aggregate 聚合根
     * @param id        标识符
     */
    void setAggregateId(@NonNull T aggregate, @NonNull Serializable id);

}
