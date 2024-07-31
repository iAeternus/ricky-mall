package com.ricky.support;

import com.ricky.context.AggregateContext;
import com.ricky.domain.diff.entity.AggregateDifference;
import com.ricky.domain.diff.utils.DifferenceUtils;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.repository.IRepository;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/19
 * @className RepositorySupport
 * @desc
 */
@Service
public abstract class RepositorySupport<T extends Aggregate<ID>, ID extends Identifier> implements IRepository<T, ID> {

    @Resource
    @Getter(AccessLevel.PROTECTED)
    private AggregateContext<T, ID> aggregateContext;

    /**
     * 这几个方法是继承的子类应该去实现的
     */
    protected abstract void doInsert(T aggregate);

    protected abstract T doSelect(ID id);

    protected abstract void doUpdate(T aggregate, AggregateDifference<T, ID> difference);

    protected abstract void doDelete(T aggregate);

    /**
     * 添加聚合根追踪
     *
     * @param aggregate 聚合根
     */
    @Override
    public void attach(T aggregate) {
        aggregateContext.attach(aggregate);
    }

    /**
     * 解除聚合根追踪
     *
     * @param aggregate 聚合根
     */
    @Override
    public void detach(T aggregate) {
        aggregateContext.detach(aggregate);
    }

    @Override
    public T find(@NotNull ID id) {
        T snapshot = aggregateContext.find(id);
        if (snapshot != null) {
            return snapshot;
        }

        T aggregate = this.doSelect(id);
        if (aggregate != null) {
            // 这里的就是让查询出来的对象能够被追踪。
            // 如果自己实现了一个定制查询接口，要记得单独调用attach。
            this.attach(aggregate);
        }
        return aggregate;
    }

    @Override
    public void remove(@NotNull T aggregate) {
        this.doDelete(aggregate);
        // 删除停止追踪
        this.detach(aggregate);
    }

    @Override
    public void save(@NotNull T aggregate) {
        // 如果没有ID，直接插入
        if (aggregate.getId() == null) {
            this.doInsert(aggregate);
            this.attach(aggregate);
            return;
        }

        // 对比差异
        T t = this.find(aggregate.getId());
        AggregateDifference<T, ID> difference = DifferenceUtils.different(t, aggregate);
        if(difference == null || difference.isEmpty()) {
            return;
        }

        // AggregateDifference<T, ID> aggregateDifference = this.aggregateContext.difference(aggregate);
        // if (aggregateDifference.isEmpty()) {
        //     return;
        // }

        // 调用update
        this.doUpdate(aggregate, difference);
        // 最终将DB带来的变化更新回AggregateContext
        this.aggregateContext.merge(aggregate);

        // AggregateDifference<T, ID> aggregateDifference = this.aggregateContext.difference(aggregate);
        // if (aggregateDifference.getDifferenceType().isInsert()) {
        //     this.doInsert(aggregate);
        // } else {
        //     this.doUpdate(aggregate, aggregateDifference);
        // }
        // this.aggregateContext.merge(aggregate);
    }

}
