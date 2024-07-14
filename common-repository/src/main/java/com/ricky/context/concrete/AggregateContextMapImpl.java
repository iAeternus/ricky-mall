package com.ricky.context.concrete;

import com.ricky.context.AggregateContext;
import com.ricky.entity.diff.EntityDiff;
import com.ricky.entity.cache.concrete.MapCacheObject;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className AggregateContextMapImpl
 * @desc
 */
@Getter
public class AggregateContextMapImpl<T extends Aggregate<ID>, ID extends Identifier> extends AggregateContext<T, ID> {

    private final MapCacheObject<T, ID> mapCacheObject;

    public AggregateContextMapImpl(MapCacheObject<T, ID> mapCacheObject) {
        this.mapCacheObject = mapCacheObject;
    }

    @Override
    public void attach(T aggregate) {

    }

    @Override
    public void detach(T aggregate) {

    }

    @Override
    public T find(ID id) {
        return null;
    }

    @Override
    public EntityDiff detectChanges(T aggregate) {
        return null;
    }

    @Override
    public void merge(T aggregate) {

    }
}
