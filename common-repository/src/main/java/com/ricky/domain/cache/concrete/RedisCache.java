package com.ricky.domain.cache.concrete;

import cn.hutool.core.util.SerializeUtil;
import com.ricky.domain.cache.Cache;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.time.Duration;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className RedisCache
 * @desc redis缓存
 */
@Service
public class RedisCache<T extends Aggregate<ID>, ID extends Identifier> extends Cache<T, ID> {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public T find(ID id) {
        Object obj = redisTemplate.opsForHash().get(super.getCacheProperties().getAppName(), id);
        if (obj == null) {
            return null;
        }

        byte[] bytes = (byte[]) obj;
        try {
            return SerializeUtil.deserialize(bytes, (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void save(ID id, T aggregate) {
        redisTemplate.opsForHash().put(super.getCacheProperties().getAppName(), id, SerializeUtil.serialize(aggregate));
        redisTemplate.expire(id, Duration.ofMillis(super.getCacheProperties().getCacheExpiresTime()));
    }

    @Override
    public void remove(ID id) {
        redisTemplate.opsForHash().delete(super.getCacheProperties().getAppName(), id);
    }

}
