package com.ricky.repository.impl;

import cn.hutool.core.collection.CollUtil;
import com.ricky.domain.diff.entity.AggregateDifference;
import com.ricky.domain.diff.entity.FieldDifference;
import com.ricky.domain.diff.entity.concrete.CollectionFieldDifference;
import com.ricky.domain.diff.enums.DifferenceType;
import com.ricky.exception.NotFoundException;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Entity;
import com.ricky.marker.Identifier;
import com.ricky.persistence.converter.AggregateDataConverter;
import com.ricky.persistence.converter.DataConverter;
import com.ricky.persistence.mapper.IMapper;
import com.ricky.persistence.po.BasePO;
import com.ricky.support.RepositorySupport;
import com.ricky.utils.CollUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className RepositoryImpl
 * @desc 持久层抽象实现类，实现了find/remove/save中的模板方法
 */
@Repository
public abstract class RepositoryImpl<T extends Aggregate<ID>, ID extends Identifier, PO extends BasePO>
        extends RepositorySupport<T, ID> /*implements AggregateDataConverter<T, ID, PO>*/ {

    // 抽象化，这里需要通过byType方式注入，否则会与派生类冲突
    @Autowired
    private IMapper<PO> mapper;
    @Autowired
    private AggregateDataConverter<T, ID, PO> dataConverter;

    @Override
    protected void doInsert(T aggregate) {
        PO po = dataConverter.toPO(aggregate);
        mapper.insert(po);
        dataConverter.setAggregateId(aggregate, po.getId());
    }

    protected abstract Map<String, List<? extends BasePO>> selectRelatedLists(PO po);

    @Override
    protected T doSelect(ID id) {
        PO po = mapper.selectById(id.getValue());
        if (po == null) {
            throw new NotFoundException("aggregate not found");
        }
        return dataConverter.toAggregate(po, selectRelatedLists(po));
    }

    protected abstract Map<String, DataConverter<Entity<Identifier>, Identifier, BasePO>> relatedEntityDataConverter();

    @Override
    @SuppressWarnings("unchecked")
    protected void doUpdate(T aggregate, AggregateDifference<T, ID> difference) {
        // TODO 大致实现了，但是存在问题，必须先插入才能修改，具体是判断是否插入还是修改的时候存在问题
        // difference.update(aggregate);
        // PO po = dataConverter.toPO(aggregate);
        // mapper.updateById(po);

        // try {
        //     if (!difference.isSelfModified()) {
        //         difference.updateChangedOnly(aggregate);
        //     }
        //     PO po = dataConverter.toPO(aggregate);
        //     mapper.updateById(po);
        // } catch (IllegalAccessException e) {
        //     throw new RuntimeException(e);
        // }

        PO po = dataConverter.toPO(aggregate);
        if (difference.isSelfModified(aggregate.getClass())) {
            mapper.updateById(po);
        }

        // 筛选出关联对象的字段差异
        List<FieldDifference> fieldDifferences = difference.filtrateRelated();
        if (CollUtil.isEmpty(fieldDifferences)) {
            throw new RuntimeException("not found in cache");
        }

        // 各个关联对象的初始值
        Map<String, List<? extends BasePO>> relatedLists = selectRelatedLists(po);

        // 获取关联对象的数据转换器
        Map<String, DataConverter<Entity<Identifier>, Identifier, BasePO>> relatedDataConverters = relatedEntityDataConverter();

        for (FieldDifference fieldDifference : fieldDifferences) {
            if (!(fieldDifference instanceof CollectionFieldDifference collectionFieldDifference)) {
                continue;
            }

            String fieldName = fieldDifference.getName();
            DataConverter<Entity<Identifier>, Identifier, BasePO> relatedDataConverter = relatedDataConverters.get(fieldName);
            if (relatedDataConverter == null) {
                throw new RuntimeException("Incorrect data converter key, key=" + fieldName);
            }

            List<FieldDifference> elementDifferences = collectionFieldDifference.getElementDifference();
            if (DifferenceType.ADDED == collectionFieldDifference.getDifferenceType()) {
                mapper.insertBatch((List<PO>) CollUtils.listConvert(elementDifferences, elementDifference -> {
                    Object tracValue = elementDifference.getTracValue();
                    return relatedDataConverter.toPO((Entity<Identifier>) tracValue);
                }));
            } else if (DifferenceType.REMOVED == fieldDifference.getDifferenceType()) {
                mapper.deleteBatchIds(CollUtils.listConvert(elementDifferences, elementDifference -> {
                    Object snapshotValue = elementDifference.getSnapshotValue();
                    return ((Entity<Identifier>) snapshotValue).getId().getValue();
                }));
            } else if (DifferenceType.MODIFIED == fieldDifference.getDifferenceType()) {
                mapper.updateBatch((List<PO>) CollUtils.listConvert(elementDifferences, elementDifference -> {
                    Object tracValue = elementDifference.getTracValue(); // TODO 检查
                    return relatedDataConverter.toPO((Entity<Identifier>) tracValue);
                }));
            } else {
                throw new RuntimeException("Incorrect difference type, type=" + fieldDifference.getDifferenceType());
            }
        }
    }

    @Override
    protected void doDelete(T aggregate) {
        ID id = aggregate.getId();
        if (id == null) {
            return;
        }
        mapper.deleteById(id.getValue());
    }

}
