package com.ricky.entity.cache.concrete;

import cn.hutool.core.util.SerializeUtil;
import com.ricky.entity.cache.Cache;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className RedisCache
 * @desc
 */
@Service
public class RedisCache<T extends Aggregate<ID>, ID extends Identifier> extends Cache<T, ID> {

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public T find(ID id) {
        Object obj = redisTemplate.opsForHash().get(super.getCacheProperties().getAppName(), id);
        if (obj == null) {
            return null;
        }

        byte[] bytes = obj.toString().getBytes();
        T aggregate = SerializeUtil.deserialize(bytes, Aggregate.class);
        redisTemplate.expire(id, Duration.ofMillis(super.getCacheProperties().getCacheExpiresTime()));
        return aggregate;
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
