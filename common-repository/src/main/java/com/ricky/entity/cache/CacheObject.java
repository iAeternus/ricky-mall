package com.ricky.entity.cache;

import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import com.ricky.properties.CacheProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className CacheObject
 * @desc 缓存对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class CacheObject<T extends Aggregate<ID>, ID extends Identifier> {

    private String appName;
    private long cacheExpiresTime;

    public abstract T find(ID id);

    public abstract void save(ID id, T aggregate);

    public abstract void remove(ID id);

}
