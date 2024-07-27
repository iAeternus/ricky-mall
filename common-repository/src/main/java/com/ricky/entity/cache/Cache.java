package com.ricky.entity.cache;

import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.properties.CacheProperties;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className Cache
 * @desc 缓存对象
 */
@Getter
@Service
public abstract class Cache<T extends Aggregate<ID>, ID extends Identifier> {

    @Resource
    private CacheProperties cacheProperties;

    public abstract T find(ID id);

    public abstract void save(ID id, T aggregate);

    public abstract void remove(ID id);

}
