package com.ricky.manager.impl;

import com.ricky.context.AggregateContext;
import com.ricky.context.AggregateContextDelegate;
import com.ricky.entity.diff.EntityDiff;
import com.ricky.manager.AggregateManager;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.properties.CacheProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className ThreadLocalAggregateManager
 * @desc
 */
@Service
public class ThreadLocalAggregateManager<T extends Aggregate<ID>, ID extends Identifier> implements AggregateManager<T, ID> {

    private final ThreadLocal<AggregateContextDelegate<T, ID>> context;

    public ThreadLocalAggregateManager(CacheProperties cacheProperties, RedisTemplate<Object, Object> redisTemplate) {
        this.context = ThreadLocal.withInitial(() -> new AggregateContextDelegate<>(cacheProperties, redisTemplate));
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
    public EntityDiff detectChanges(T aggregate) {
        return context.get().detectChanges(aggregate);
    }

    @Override
    public void merge(T aggregate) {
        context.get().merge(aggregate);
    }
}
