package com.ricky.support;

import com.ricky.context.AggregateContext;
import com.ricky.entity.diff.AggregateDiff;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.repository.IRepository;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.context.annotation.DependsOn;
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
    protected abstract void onInsert(T aggregate);

    protected abstract T onSelect(ID id);

    protected abstract void onUpdate(T aggregate, AggregateDiff<T, ID> diff);

    protected abstract void onDelete(T aggregate);

    @Override
    public void attach(T aggregate) {
        aggregateContext.attach(aggregate);
    }

    @Override
    public void detach(T aggregate) {
        aggregateContext.detach(aggregate);
    }

    @Override
    public T find(@NotNull ID id) {
        T aggregate = this.onSelect(id);
        if (aggregate != null) {
            // 这里的就是让查询出来的对象能够被追踪。
            // 如果自己实现了一个定制查询接口，要记得单独调用attach。
            this.attach(aggregate);
        }
        return aggregate;
    }

    @Override
    public void remove(@NotNull T aggregate) {
        this.onDelete(aggregate);
        // 删除停止追踪
        this.detach(aggregate);
    }

    @Override
    public void save(@NotNull T aggregate) {
        // 如果没有ID，直接插入
        if (aggregate.getId() == null) {
            this.onInsert(aggregate);
            this.attach(aggregate);
            return;
        }

        // 做Diff
        AggregateDiff<T, ID> diff = aggregateContext.detectChanges(aggregate);
        if (diff.isEmpty()) {
            return;
        }

        // 调用UPDATE
        this.onUpdate(aggregate, diff);

        // 最终将DB带来的变化更新回AggregateManager
        aggregateContext.merge(aggregate);
    }

}
