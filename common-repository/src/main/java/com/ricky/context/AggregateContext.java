package com.ricky.context;

import com.ricky.domain.cache.CacheDelegate;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifiable;
import com.ricky.marker.Identifier;
import com.ricky.utils.ReflectionUtils;
import com.ricky.utils.SnapshotUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className AggregateContext
 * @desc 聚合根上下文
 */
@Service
public class AggregateContext<T extends Aggregate<ID>, ID extends Identifier> {

    @Resource
    private CacheDelegate<T, ID> cache;

    public void attach(T aggregate) {
        if (aggregate.getId() != null) {
            this.merge(aggregate);
        }
    }

    public void detach(T aggregate) {
        if (aggregate.getId() != null) {
            cache.remove(aggregate.getId());
        }
    }

    public T find(ID id) {
        return cache.find(id);
    }

    // public AggregateDifference<T, ID> difference(T aggregate) {
    //     T snapshot = cache.find(aggregate.getId());
    //     if (snapshot == null) {
    //         attach(aggregate);
    //     }
    //     return DifferenceUtils.different(snapshot, aggregate);
    //
    //     // if (aggregate.getId() == null) {
    //     //     return null;
    //     // }
    //     // T snapshot = cacheDelegate.find(aggregate.getId());
    //     // if (snapshot == null) {
    //     //     attach(aggregate);
    //     // }
    //     // return AggregateDiff.<T, ID>newInstance().diff(snapshot, aggregate);
    // }

    public void merge(T aggregate) {
        if (aggregate.getId() != null) {
            T snapshot = SnapshotUtils.snapshot(aggregate);
            cache.save(aggregate.getId(), snapshot);
        }
    }

    public void setId(T aggregate, ID id) {
        ReflectionUtils.writeField(Identifiable.ID, aggregate, id);
    }

}
