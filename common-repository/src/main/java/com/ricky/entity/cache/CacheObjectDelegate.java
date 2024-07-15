package com.ricky.entity.cache;

import com.ricky.entity.cache.concrete.MapCacheObject;
import com.ricky.entity.cache.concrete.RedisCacheObject;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.properties.CacheProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/15
 * @className CacheObjectDelegate
 * @desc 缓存委派
 */
@Service
@RequiredArgsConstructor
public class CacheObjectDelegate<T extends Aggregate<ID>, ID extends Identifier> extends CacheObject<T, ID> {

    private String cacheType;

    private final CacheProperties cacheProperties;
    private final RedisTemplate<Object, Object> redisTemplate;

    @PostConstruct
    public void afterInit() {
        String type = cacheProperties.getType();
        if (type == null) {
            throw new RuntimeException("未配置 cache.type, 请关注");
        }
        this.cacheType = type;
        initContextMap();
    }

    private final Map<String, CacheObject<T, ID>> contextMap = new HashMap<>();

    private void initContextMap() {
        contextMap.put(CacheProperties.MAP, new MapCacheObject<>(
                cacheProperties.getAppName(),
                cacheProperties.getCacheExpiresTime(),
                new HashMap<>()
        ));
        contextMap.put(CacheProperties.REDIS, new RedisCacheObject<>(
                cacheProperties.getAppName(),
                cacheProperties.getCacheExpiresTime(),
                redisTemplate
        ));
    }

    public CacheObject<T, ID> selectImpl(String type) {
        if (!contextMap.containsKey(type)) {
            throw new RuntimeException("不支持的cache类型, cacheType=" + cacheType);
        }
        return contextMap.get(type);
    }

    @Override
    public T find(ID id) {
        return selectImpl(cacheType).find(id);
    }

    @Override
    public void save(ID id, T aggregate) {
        selectImpl(cacheType).save(id, aggregate);
    }

    @Override
    public void remove(ID id) {
        selectImpl(cacheType).remove(id);
    }

}
