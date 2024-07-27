package com.ricky.entity.cache;

import com.ricky.entity.cache.concrete.MapCache;
import com.ricky.entity.cache.concrete.RedisCache;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.properties.CacheProperties;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/15
 * @className CacheDelegate
 * @desc 缓存委派
 */
@Service
public class CacheDelegate<T extends Aggregate<ID>, ID extends Identifier> extends Cache<T, ID> {

    private String cacheType;

    @Resource
    private CacheProperties cacheProperties;

    @PostConstruct
    public void afterInit() {
        String type = cacheProperties.getType();
        if (type == null) {
            throw new RuntimeException("未配置 cache.type, 请关注");
        }
        this.cacheType = type;
        initContextMap();
    }

    private final Map<String, Cache<T, ID>> contextMap = new HashMap<>();

    private void initContextMap() {
        if(cacheProperties.getAppName() == null) {
            throw new RuntimeException("未配置 cache.appName, 请关注");
        }

        // 具体的实现类
        contextMap.put(CacheProperties.MAP, new MapCache<>());
        contextMap.put(CacheProperties.REDIS, new RedisCache<>());
    }

    public Cache<T, ID> selectImpl(String type) {
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
