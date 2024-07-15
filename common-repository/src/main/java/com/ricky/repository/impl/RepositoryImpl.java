package com.ricky.repository.impl;

import com.ricky.entity.diff.AggregateDiff;
import com.ricky.exception.NotFoundException;
import com.ricky.manager.AggregateManager;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifiable;
import com.ricky.marker.Identifier;
import com.ricky.persistence.converter.AggregateDataConverter;
import com.ricky.persistence.mapper.IMapper;
import com.ricky.persistence.po.BasePO;
import com.ricky.repository.IRepository;
import com.ricky.support.RepositorySupport;
import com.ricky.utils.ReflectionUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className RepositoryImpl
 * @desc
 */
@Repository
@DependsOn("IMapper")
public abstract class RepositoryImpl<T extends Aggregate<ID>, ID extends Identifier, PO extends BasePO>
        extends RepositorySupport<T, ID> implements IRepository<T, ID>, AggregateDataConverter<T, ID, PO> {

    private final IMapper<PO> mapper;

    public RepositoryImpl(AggregateManager<T, ID> aggregateManager, IMapper<PO> mapper) {
        super(aggregateManager);
        this.mapper = mapper;
    }

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
        return toAggregate(po);
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
