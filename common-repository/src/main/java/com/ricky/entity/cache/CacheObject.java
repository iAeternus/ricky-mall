package com.ricky.entity.cache;

import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className CacheObject
 * @desc 缓存对象
 */
@Data
@Component
@NoArgsConstructor
public abstract class CacheObject<T extends Aggregate<ID>, ID extends Identifier> {

    private String appName;
    private long cacheExpiresTime;

    public CacheObject(String appName, long cacheExpiresTime) {
        this.appName = appName;
        this.cacheExpiresTime = cacheExpiresTime;
    }

    public abstract T find(ID id);

    public abstract void save(ID id, T aggregate);

    public abstract void remove(ID id);

}
