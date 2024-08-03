package com.ricky.domain.cache.concrete;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.ricky.domain.cache.Cache;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/8/3
 * @className CaffeineCache
 * @desc 利用Caffeine实现的本地缓存
 */
@Service
public class CaffeineCache<T extends Aggregate<ID>, ID extends Identifier> extends Cache<T, ID> {

    private com.github.benmanes.caffeine.cache.Cache<ID, T> cache;

    @PostConstruct
    private void init() {
        cache = Caffeine.newBuilder()
                .expireAfterWrite(super.getCacheProperties().getCacheExpiresTime(), TimeUnit.MILLISECONDS)
                .maximumSize(super.getCacheProperties().getCacheExpiresSize())
                .build();
    }

    @Override
    public synchronized T find(ID id) {
        return cache.getIfPresent(id);
    }

    @Override
    public synchronized void save(ID id, T aggregate) {
        cache.put(id, aggregate);
    }

    @Override
    public synchronized void remove(ID id) {
        cache.invalidate(id);
    }
}
