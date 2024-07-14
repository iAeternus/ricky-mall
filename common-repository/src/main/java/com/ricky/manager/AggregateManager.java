package com.ricky.manager;

import com.ricky.entity.diff.EntityDiff;
import com.ricky.manager.impl.ThreadLocalAggregateManager;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.properties.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/6/18
 * @className AggregateManager
 * @desc
 */
@Service
public interface AggregateManager<T extends Aggregate<ID>, ID extends Identifier> {

    static <T extends Aggregate<ID>, ID extends Identifier> AggregateManager<T, ID> newInstance(CacheProperties cacheProperties, RedisTemplate<Object, Object> redisTemplate) {
        return new ThreadLocalAggregateManager<>(cacheProperties, redisTemplate);
    }

    void attach(T aggregate);

    void attach(T aggregate, ID id);

    void detach(T aggregate);

    T find(ID id);

    EntityDiff detectChanges(T aggregate);

    void merge(T aggregate);

}
