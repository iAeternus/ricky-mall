package com.ricky.repository.impl;

import com.ricky.entity.diff.AggregateDiff;
import com.ricky.exception.NotFoundException;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifiable;
import com.ricky.marker.Identifier;
import com.ricky.persistence.converter.AggregateDataConverter;
import com.ricky.persistence.mapper.IMapper;
import com.ricky.persistence.po.BasePO;
import com.ricky.repository.IRepository;
import com.ricky.support.RepositorySupport;
import com.ricky.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className RepositoryImpl
 * @desc 持久层抽象实现类，实现了模板方法find/remove/save
 */
@Repository
@DependsOn("IMapper")
public abstract class RepositoryImpl<T extends Aggregate<ID>, ID extends Identifier, PO extends BasePO>
        extends RepositorySupport<T, ID> implements IRepository<T, ID>, AggregateDataConverter<T, ID, PO> {

    @Autowired
    private IMapper<PO> mapper;

    @Override
    protected void onInsert(T aggregate) {
        PO po = toPO(aggregate);
        mapper.insert(po);
        ReflectionUtils.writeField(Identifiable.ID, aggregate, po.getId());
    }

    // protected abstract Map<Class<?>, ? extends IMapper<?>> acquireRelatedMappers();

    // private Map<Class<?>, List<? extends BasePO>> selectRelatedObjects(PO po) {
    //     Map<Class<?>, List<? extends BasePO>> map = new HashMap<>();
    //     Map<Class<?>, ? extends IMapper<?>> relatedMappers = acquireRelatedMappers();
    //     Set<Class<?>> classes = relatedMappers.keySet();
    //     for (Class<?> clazz : classes) {
    //         List<? extends BasePO> relatedObjects = relatedMappers.get(clazz).selectList(
    //                 new QueryWrapper<>().lambda().eq()
    //         );
    //         if(CollUtil.isEmpty(relatedObjects)) {
    //             relatedObjects = Collections.emptyList();
    //         }
    //         map.put(clazz, relatedObjects);
    //     }
    //     return map;
    // }

    protected abstract Map<Class<?>, List<? extends BasePO>> selectRelatedObjects(PO po);

    protected abstract Long getIdValue(ID id);

    @Override
    protected T onSelect(ID id) {
        PO po = mapper.selectById(getIdValue(id));
        // PO po = selectPOById(id);
        if (po == null) {
            throw new NotFoundException();
        }
        return toAggregate(po, selectRelatedObjects(po));
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
