package com.ricky.context;

import com.ricky.context.concrete.AggregateContextCacheImpl;
import com.ricky.context.concrete.AggregateContextMapImpl;
import com.ricky.entity.diff.EntityDiff;
import com.ricky.entity.cache.concrete.MapCacheObject;
import com.ricky.entity.cache.concrete.RedisCacheObject;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.properties.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className AggregateContextDelegate
 * @desc 聚合根上下文委派
 */
@Service
public class AggregateContextDelegate<T extends Aggregate<ID>, ID extends Identifier> extends AggregateContext<T, ID> {

    private final String cacheType;

    private final CacheProperties cacheProperties;
    private final RedisTemplate<Object, Object> redisTemplate;

    public AggregateContextDelegate(CacheProperties cacheProperties, RedisTemplate<Object, Object> redisTemplate) {
        this.cacheProperties = cacheProperties;
        this.redisTemplate = redisTemplate;
        String type = cacheProperties.getType();
        if(type == null) {
            throw new RuntimeException("未配置 cache.type, 请关注");
        }
        this.cacheType = type;
    }

    public AggregateContext<T, ID> selectImpl(String type) {
        if(CacheProperties.MAP.equals(type)) {
            return new AggregateContextMapImpl<>(new MapCacheObject<>(
                    cacheProperties.getAppName(),
                    cacheProperties.getCacheExpiresTime(),
                    new HashMap<>()
            ));
        }
        if(CacheProperties.REDIS.equals(type)) {
            return new AggregateContextCacheImpl<>(new RedisCacheObject<>(
                    cacheProperties.getAppName(),
                    cacheProperties.getCacheExpiresTime(),
                    redisTemplate
            ));
        }
        throw new RuntimeException("不支持的cache类型, cacheType=" + cacheType);
    }

    @Override
    public void attach(T aggregate) {
        selectImpl(cacheType).attach(aggregate);
    }

    @Override
    public void detach(T aggregate) {
        selectImpl(cacheType).detach(aggregate);
    }

    @Override
    public T find(ID id) {
        return selectImpl(cacheType).find(id);
    }

    @Override
    public EntityDiff detectChanges(T aggregate) {
        return selectImpl(cacheType).detectChanges(aggregate);
    }

    @Override
    public void merge(T aggregate) {
        selectImpl(cacheType).merge(aggregate);
    }

}
