package com.ricky.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ricky.domain.diff.entity.AggregateDifference;
import com.ricky.domain.diff.entity.FieldDifference;
import com.ricky.domain.diff.entity.concrete.CollectionFieldDifference;
import com.ricky.domain.diff.enums.DifferenceType;
import com.ricky.exception.NotFoundException;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Entity;
import com.ricky.marker.Identifier;
import com.ricky.persistence.converter.AggregateDataConverter;
import com.ricky.persistence.converter.AssociationDataConverter;
import com.ricky.persistence.mapper.IMapper;
import com.ricky.persistence.po.BasePO;
import com.ricky.support.RepositorySupport;
import com.ricky.utils.CollUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className RepositoryImpl
 * @desc 持久层抽象实现类，实现了find/remove/save中的模板方法
 */
@Repository
public abstract class RepositoryImpl<T extends Aggregate<ID>, ID extends Identifier, PO extends BasePO> extends RepositorySupport<T, ID> {

    // 抽象化，这里需要通过byType方式注入，否则会与派生类冲突
    @Autowired
    private IMapper<PO> mapper;
    @Autowired
    private AggregateDataConverter<T, ID, PO> dataConverter;

    /**
     * 获取关联对象的mapper
     *
     * @param <M> IMapper 的子类
     * @param <P> BasePO的子类
     * @return 返回Map，键-字段名，值-关联对象的mapper
     */
    protected abstract <M extends IMapper<P>, P extends BasePO> Map<String, M> relatedMappers();

    /**
     * 获取关联对象外键列名
     *
     * @return 返回Map，键-字段名，值-外键的列名
     */
    protected abstract Map<String, String> relatedColumnNames();

    /**
     * 获取所有关联实体数据转换器，需要派生类实现
     *
     * @param <E> 实体
     * @param <I> 实体标识符
     * @param <P> PO持久化对象
     * @return 返回Map，键-聚合根中的关联对象字段名，值-数据转换器
     */
    protected abstract <E extends Entity<I>, I extends Identifier, P extends BasePO> Map<String, AssociationDataConverter<E, I, P>> relatedDataConverters();

    @Override
    @Transactional
    public void doInsert(T aggregate) {
        PO po = dataConverter.convert(aggregate);
        mapper.insert(po);
        dataConverter.setAggregateId(aggregate, po.getId());

        // 获取关联对象的mapper
        Map<String, IMapper<BasePO>> relatedMappers = relatedMappers();
        if (CollUtils.isEmpty(relatedMappers)) {
            return;
        }

        // 获取需要插入的关联对象列表的集合
        Map<String, List<BasePO>> relatedPOLists = dataConverter.getAssociationPOLists(aggregate);
        // handle associations
        relatedPOLists.forEach((fieldName, relatedObjects) -> {
            if (CollUtils.isEmpty(relatedObjects)) {
                return;
            }

            // 获取对应的mapper
            IMapper<BasePO> relatedMapper = relatedMappers.get(fieldName);
            if (relatedMapper == null) {
                throw new RuntimeException("Incorrect key of related mapper, key=" + fieldName);
            }

            relatedMapper.insertBatch(relatedObjects);
        });
    }

    @Override
    protected T doSelect(ID id) {
        PO po = mapper.selectById(id.getValue());
        if (po == null) {
            throw new NotFoundException("aggregate not found, id=" + id.getValue());
        }

        // 获取关联对象的mapper
        Map<String, IMapper<BasePO>> relatedMappers = relatedMappers();
        if (CollUtils.isEmpty(relatedMappers)) {
            return dataConverter.convert(po, null);
        }

        // 获取关联对象的外键
        Map<String, String> relatedColumns = relatedColumnNames();

        return dataConverter.convert(po, selectRelatedLists(po, relatedMappers, relatedColumns));
    }

    private Map<String, List<BasePO>> selectRelatedLists(PO po,
                                                         Map<String, IMapper<BasePO>> relatedMappers,
                                                         Map<String, String> relatedColumns) {
        Map<String, List<BasePO>> relatedLists = new HashMap<>();
        relatedMappers.forEach((fieldName, mapper) -> {
            List<BasePO> relatedPOS = mapper.selectList(new QueryWrapper<BasePO>()
                    .eq(relatedColumns.get(fieldName), po.getId()));

            if (CollUtils.isEmpty(relatedPOS)) {
                relatedLists.put(fieldName, Collections.emptyList());
            } else {
                relatedLists.put(fieldName, relatedPOS);
            }
        });
        return relatedLists;
    }

    @Override
    @Transactional
    public void doUpdate(T aggregate, AggregateDifference<T, ID> difference) {
        PO po = dataConverter.convert(aggregate);
        if (difference.isSelfModified(aggregate.getClass())) {
            mapper.updateById(po);
        }

        // 筛选出关联对象的字段差异
        List<FieldDifference> fieldDifferences = difference.filtrateRelated();
        if (CollUtils.isEmpty(fieldDifferences)) {
            return;
        }

        // 获取关联对象的数据转换器
        Map<String, AssociationDataConverter<Entity<Identifier>, Identifier, BasePO>> relatedEntityDataConverters = relatedDataConverters();
        if (CollUtils.isEmpty(relatedEntityDataConverters)) {
            return;
        }

        // 获取关联对象的mapper
        Map<String, IMapper<BasePO>> relatedMappers = relatedMappers();
        if (CollUtils.isEmpty(relatedMappers)) {
            return;
        }

        // handle associations
        for (FieldDifference fieldDifference : fieldDifferences) {
            if (!(fieldDifference instanceof CollectionFieldDifference collectionFieldDifference)) {
                continue;
            }

            String fieldName = fieldDifference.getName();

            // 获取对应的的数据转换器
            AssociationDataConverter<Entity<Identifier>, Identifier, BasePO> associationDataConverter = relatedEntityDataConverters.get(fieldName);
            if (associationDataConverter == null) {
                throw new RuntimeException("Incorrect key of related data converters, key=" + fieldName);
            }

            // 获取对应的mapper
            IMapper<BasePO> relatedMapper = relatedMappers.get(fieldName);
            if (relatedMapper == null) {
                throw new RuntimeException("Incorrect key of related mappers, key=" + fieldName);
            }

            List<FieldDifference> elementDifferences = collectionFieldDifference.getElementDifference();

            elementDifferences.stream()
                    .collect(Collectors.groupingBy(FieldDifference::getDifferenceType))
                    .forEach((differenceType, differences) ->
                            handleRelatedEntity(aggregate, fieldDifference, differences, relatedMapper, associationDataConverter));
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Aggregate<ID>, ID extends Identifier> void handleRelatedEntity(T aggregate, FieldDifference fieldDifference, List<FieldDifference> elementDifferences,
                                                                                             IMapper<BasePO> relatedMapper, AssociationDataConverter<Entity<Identifier>, Identifier, BasePO> relatedDataConverter) {
        DifferenceType differenceType = elementDifferences.get(0).getDifferenceType();
        switch (differenceType) {
            case ADDED -> relatedMapper.insertBatch(CollUtils.listConvert(elementDifferences, elementDifference -> {
                Object tracValue = elementDifference.getTracValue();
                Entity<Identifier> entity = (Entity<Identifier>) tracValue;
                return relatedDataConverter.convert(entity, aggregate.getId().getValue()); // 设置外键
            }));
            case REMOVED -> relatedMapper.deleteBatchIds(CollUtils.listConvert(elementDifferences, elementDifference -> {
                Object snapshotValue = elementDifference.getSnapshotValue();
                return ((Entity<Identifier>) snapshotValue).getId().getValue();
            }));
            case MODIFIED -> relatedMapper.updateBatch(CollUtils.listConvert(elementDifferences, elementDifference -> {
                Object tracValue = elementDifference.getTracValue();
                return relatedDataConverter.convert((Entity<Identifier>) tracValue);
            }));
            default -> throw new RuntimeException("Incorrect difference type, type=" + fieldDifference.getType());
        }
    }

    @Override
    @Transactional
    public void doDelete(T aggregate) {
        ID id = aggregate.getId();
        if (id == null) {
            return;
        }

        mapper.deleteById(id.getValue());

        // 获取关联对象的mapper
        Map<String, IMapper<BasePO>> relatedMappers = relatedMappers();
        if (CollUtils.isEmpty(relatedMappers)) {
            return;
        }

        // 获取关联对象的外键
        Map<String, String> relatedColumns = relatedColumnNames();

        // handle associations
        relatedMappers.forEach((fieldName, mapper) -> mapper.delete(new UpdateWrapper<BasePO>()
                .eq(relatedColumns.get(fieldName), aggregate.getId().getValue())));
    }

}
