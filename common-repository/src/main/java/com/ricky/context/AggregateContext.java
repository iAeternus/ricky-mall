package com.ricky.context;

import com.ricky.entity.cache.CacheObjectDelegate;
import com.ricky.entity.diff.AggregateDiff;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifiable;
import com.ricky.marker.Identifier;
import com.ricky.utils.ReflectionUtils;
import com.ricky.utils.SnapshotUtils;
import org.springframework.context.annotation.DependsOn;
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
@DependsOn("cacheObjectDelegate")
public class AggregateContext<T extends Aggregate<ID>, ID extends Identifier> {

    @Resource
    private CacheObjectDelegate<T, ID> cacheObjectDelegate;

    public void attach(T aggregate) {
        if (aggregate.getId() != null && cacheObjectDelegate.find(aggregate.getId()) != null) {
            this.merge(aggregate);
        }
    }

    public void detach(T aggregate) {
        if (aggregate.getId() != null) {
            cacheObjectDelegate.remove(aggregate.getId());
        }
    }

    public T find(ID id) {
        return cacheObjectDelegate.find(id);
    }

    public AggregateDiff<T, ID> detectChanges(T aggregate) {
        if (aggregate.getId() == null) {
            return null;
        }
        T snapshot = cacheObjectDelegate.find(aggregate.getId());
        if (snapshot == null) {
            attach(aggregate);
        }
        return AggregateDiff.<T, ID>newInstance().diff(snapshot, aggregate);
    }

    public void merge(T aggregate) {
        if (aggregate.getId() != null) {
            T snapshot = SnapshotUtils.snapshot(aggregate);
            cacheObjectDelegate.save(aggregate.getId(), snapshot);
        }
    }

    public void setId(T aggregate, ID id) {
        ReflectionUtils.writeField(Identifiable.ID, aggregate, id);
    }

}
