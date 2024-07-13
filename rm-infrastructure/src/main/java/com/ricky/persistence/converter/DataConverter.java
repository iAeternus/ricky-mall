package com.ricky.persistence.converter;

import com.ricky.marker.Entity;
import com.ricky.marker.Identifier;
import com.ricky.persistence.po.BasePO;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/8
 * @className DataConverter
 * @desc 数据转换器接口
 */
@Service
public interface DataConverter<E extends Entity<ID>, ID extends Identifier, PO extends BasePO> {

    /**
     * 转换领域对象DO为持久化对象PO
     *
     * @param entity 领域对象DO
     * @return 持久化对象PO
     */
    PO toPO(@NonNull E entity);

    /**
     * 转换持久化对象PO为领域对象DO
     *
     * @param po 持久化对象PO
     * @return 领域对象DO
     */
    E toEntity(@NonNull PO po);

}
