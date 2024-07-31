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
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void doInsert(T aggregate) {
        PO po = dataConverter.toPO(aggregate);
        mapper.insert(po);
        dataConverter.setAggregateId(aggregate, po.getId());

        // 获取关联对象的mapper
        Map<String, IMapper<BasePO>> relatedMappers = gainRelatedMappers();

        // 获取需要插入的关联对象列表的集合
        Map<String, List<BasePO>> relatedPOLists = dataConverter.toRelatedPOLists(aggregate);
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

    /**
     * 查询所有关联对象列表，需要派生类实现
     *
     * @param po 一般提供外键id
     * @return 返回Map，键-聚合根中的关联对象字段名，值-关联对象列表
     */
    protected abstract <P extends BasePO> Map<String, List<P>> selectRelatedLists(PO po);

    protected abstract <M extends IMapper<P>, P extends BasePO> Map<String, M> gainRelatedMappers();

    // private <P extends BasePO> Map<String, List<P>> relatedLists;

    @Override
    protected T doSelect(ID id) {
        PO po = mapper.selectById(id.getValue());
        if (po == null) {
            throw new NotFoundException("aggregate not found");
        }
        return dataConverter.toAggregate(po, selectRelatedLists(po));
    }

    /**
     * 获取所有关联实体数据转换器，需要派生类实现
     *
     * @param <E> 实体
     * @param <I> 实体标识符
     * @param <P> PO持久化对象
     * @return 返回Map，键-聚合根中的关联对象字段名，值-数据转换器
     */
    protected abstract <E extends Entity<I>, I extends Identifier, P extends BasePO> Map<String, DataConverter<E, I, P>> gainRelatedEntityDataConverters();

    protected abstract <I extends Identifier> void setForeignKey(Aggregate<ID> aggregate, Entity<I> entity);

    @Override
    @Transactional
    public void doUpdate(T aggregate, AggregateDifference<T, ID> difference) {
        PO po = dataConverter.toPO(aggregate);
        if (difference.isSelfModified(aggregate.getClass())) {
            mapper.updateById(po);
        }

        // 筛选出关联对象的字段差异
        List<FieldDifference> fieldDifferences = difference.filtrateRelated();
        if (CollUtil.isEmpty(fieldDifferences)) {
            throw new RuntimeException("not found in cache");
        }

        // 获取关联对象的数据转换器
        Map<String, DataConverter<Entity<Identifier>, Identifier, BasePO>> relatedEntityDataConverters = gainRelatedEntityDataConverters();
        // 获取关联对象的mapper
        Map<String, IMapper<BasePO>> relatedMappers = gainRelatedMappers();

        // 处理关联对象
        for (FieldDifference fieldDifference : fieldDifferences) {
            if (!(fieldDifference instanceof CollectionFieldDifference collectionFieldDifference)) {
                continue;
            }

            String fieldName = fieldDifference.getName();

            // 获取对应的的数据转换器
            DataConverter<Entity<Identifier>, Identifier, BasePO> relatedDataConverter = relatedEntityDataConverters.get(fieldName);
            if (relatedDataConverter == null) {
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
                            handleRelatedEntity(aggregate, fieldDifference, differences, relatedMapper, relatedDataConverter));
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends Aggregate<ID>, ID extends Identifier> void handleRelatedEntity(T aggregate, FieldDifference fieldDifference, List<FieldDifference> elementDifferences,
                                                                                             IMapper<BasePO> relatedMapper, DataConverter<Entity<Identifier>, Identifier, BasePO> relatedDataConverter) {
        DifferenceType differenceType = elementDifferences.get(0).getDifferenceType();
        if (DifferenceType.ADDED == differenceType) {
            relatedMapper.insertBatch(CollUtils.listConvert(elementDifferences, elementDifference -> {
                Object tracValue = elementDifference.getTracValue();
                Entity<Identifier> entity = (Entity<Identifier>) tracValue;
                // // 新值要设置外键
                // entity.setId(aggregate.getId());
                return relatedDataConverter.toPO(entity); // TODO 查看前端传值 Image
            }));
        } else if (DifferenceType.REMOVED == differenceType) {
            relatedMapper.deleteBatchIds(CollUtils.listConvert(elementDifferences, elementDifference -> {
                Object snapshotValue = elementDifference.getSnapshotValue();
                return ((Entity<Identifier>) snapshotValue).getId().getValue();
            }));
        } else if (DifferenceType.MODIFIED == differenceType) {
            relatedMapper.updateBatch(CollUtils.listConvert(elementDifferences, elementDifference -> {
                Object tracValue = elementDifference.getTracValue(); // TODO 检查
                return relatedDataConverter.toPO((Entity<Identifier>) tracValue);
            }));
        } else {
            throw new RuntimeException("Incorrect difference type, type=" + fieldDifference.getType());
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
