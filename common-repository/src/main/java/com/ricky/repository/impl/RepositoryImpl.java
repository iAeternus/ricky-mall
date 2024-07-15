package com.ricky.repository.impl;

import com.ricky.entity.diff.AggregateDiff;
import com.ricky.exception.NotFoundException;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifiable;
import com.ricky.marker.Identifier;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.mapper.IMapper;
import com.ricky.persistence.po.BasePO;
import com.ricky.repository.Repository;
import com.ricky.support.RepositorySupport;
import com.ricky.utils.ReflectionUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className RepositoryImpl
 * @desc
 */
@Service
// @RequiredArgsConstructor
public abstract class RepositoryImpl<T extends Aggregate<ID>, ID extends Identifier, PO extends BasePO> extends RepositorySupport<T, ID> implements Repository<T, ID> {

    // @Resource
    // private final DataConverter<T, ID, PO> dataConverter;

    @Resource
    private IMapper<PO> mapper;

    /**
     * 转换领域对象DO为持久化对象PO
     *
     * @param aggregate 领域对象DO
     * @return 持久化对象PO
     */
    public abstract PO toPO(@NonNull T aggregate);

    /**
     * 转换持久化对象PO为领域对象DO
     *
     * @param po 持久化对象PO
     * @return 领域对象DO
     */
    public abstract T toEntity(@NonNull PO po);

    @Override
    protected void onInsert(T aggregate) {
        PO po = toPO(aggregate);
        mapper.insert(po);
        ReflectionUtils.writeField(Identifiable.ID, aggregate, po.getId());
    }

    @Override
    protected T onSelect(ID id) {
        PO po = mapper.selectById(id);
        if(po == null) {
            throw new NotFoundException();
        }
        return toEntity(po);
    }

    @Override
    protected void onUpdate(T aggregate, AggregateDiff<T, ID> diff) {
        try {
            if (!diff.isSelfModified()) {
                diff.updateChangedOnly(aggregate);
            }
            PO po = toPO(aggregate);
            mapper.updateById(po);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onDelete(T aggregate) {
        PO po = toPO(aggregate);
        mapper.deleteById(po);
    }
}
