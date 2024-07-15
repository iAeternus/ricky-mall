package com.ricky.entity.cache.concrete;

import cn.hutool.core.util.SerializeUtil;
import com.ricky.entity.cache.CacheObject;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className RedisCacheObject
 * @desc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RedisCacheObject<T extends Aggregate<ID>, ID extends Identifier> extends CacheObject<T, ID> {

    private RedisTemplate<Object, Object> redisTemplate;

    public RedisCacheObject(String appName, long cacheExpiresTime, RedisTemplate<Object, Object> redisTemplate) {
        super(appName, cacheExpiresTime);
        this.redisTemplate = redisTemplate;
    }

    @Override
    public T find(ID id) {
        Object obj = redisTemplate.opsForHash().get(getAppName(), id);
        if (obj == null) {
            return null;
        }

        byte[] bytes = obj.toString().getBytes();
        T aggregate = SerializeUtil.deserialize(bytes, Aggregate.class);
        redisTemplate.expire(id, Duration.ofMillis(getCacheExpiresTime()));
        return aggregate;
    }

    @Override
    public void save(ID id, T aggregate) {
        redisTemplate.opsForHash().put(getAppName(), id, SerializeUtil.serialize(aggregate));
        redisTemplate.expire(id, Duration.ofMillis(getCacheExpiresTime()));
    }

    @Override
    public void remove(ID id) {
        redisTemplate.opsForHash().delete(getAppName(), id);
    }

}
