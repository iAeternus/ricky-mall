package com.ricky.manager.impl;

import com.ricky.context.AggregateContext;
import com.ricky.entity.diff.AggregateDiff;
import com.ricky.manager.AggregateManager;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className ThreadLocalAggregateManager
 * @desc 通过ThreadLocal避免多线程公用同一个Entity的情况
 */
@Service
public class ThreadLocalAggregateManager<T extends Aggregate<ID>, ID extends Identifier> implements AggregateManager<T, ID> {

    private final ThreadLocal<AggregateContext<T, ID>> context;

    public ThreadLocalAggregateManager() {
        this.context = ThreadLocal.withInitial(AggregateContext::new);
    }

    @Override
    public void attach(T aggregate) {
        context.get().attach(aggregate);
    }

    @Override
    public void attach(T aggregate, ID id) {
        context.get().setId(aggregate, id);
        context.get().attach(aggregate);
    }

    @Override
    public void detach(T aggregate) {
        context.get().detach(aggregate);
    }

    @Override
    public T find(ID id) {
        return context.get().find(id);
    }

    @Override
    public AggregateDiff<T, ID> detectChanges(T aggregate) {
        return context.get().detectChanges(aggregate);
    }

    @Override
    public void merge(T aggregate) {
        context.get().merge(aggregate);
    }
}
