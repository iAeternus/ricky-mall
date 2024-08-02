package com.ricky.persistence.converter;

import com.ricky.marker.Entity;
import com.ricky.marker.Identifier;
import com.ricky.persistence.po.BasePO;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/31
 * @className AssociationDataConverter
 * @desc
 */
@Service
public interface AssociationDataConverter<E extends Entity<ID>, ID extends Identifier, P extends BasePO> extends DataConverter<E, ID, P> {

    /**
     * 转换领域对象DO为持久化对象PO
     *
     * @param entity     领域对象DO
     * @param foreignKey 关联对象所需要的外键
     * @return 持久化对象PO
     */
    P convert(E entity, Serializable foreignKey);

}
