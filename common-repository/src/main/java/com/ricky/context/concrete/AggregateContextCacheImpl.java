package com.ricky.context.concrete;

import com.ricky.context.AggregateContext;
import com.ricky.entity.diff.EntityDiff;
import com.ricky.entity.cache.concrete.RedisCacheObject;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className AggregateContextCacheImpl
 * @desc
 */
@Getter
public class AggregateContextCacheImpl<T extends Aggregate<ID>, ID extends Identifier> extends AggregateContext<T, ID> {

    private final RedisCacheObject<T, ID> redisCacheObject;

    public AggregateContextCacheImpl(RedisCacheObject<T, ID> redisCacheObject) {
        this.redisCacheObject = redisCacheObject;
    }

    @Override
    public void attach(T aggregate) {
    }

    @Override
    public void detach(T aggregate) {

    }

    @Override
    public T find(ID id) {
        return redisCacheObject.find(id);
    }

    @Override
    public EntityDiff detectChanges(T aggregate) {
        T t = redisCacheObject.find(aggregate.getId());
        // todo diff
        return null;
    }

    @Override
    public void merge(T aggregate) {
        redisCacheObject.save(aggregate.getId(), aggregate);
    }

}
