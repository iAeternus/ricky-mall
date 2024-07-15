package com.ricky.entity.cache.concrete;

import cn.hutool.core.util.SerializeUtil;
import com.ricky.entity.cache.CacheObject;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className MapCacheObject
 * @desc
 */
@Data
@Component
@EqualsAndHashCode(callSuper = true)
public class MapCacheObject<T extends Aggregate<ID>, ID extends Identifier> extends CacheObject<T, ID> {

    private Map<ID, T> cacheMap = new HashMap<>();

    public MapCacheObject(String appName, long cacheExpiresTime) {
        super(appName, cacheExpiresTime);
    }

    @Override
    public T find(ID id) {
        return cacheMap.get(id);
    }

    @Override
    public void save(ID id, T aggregate) {
        cacheMap.put(id, SerializeUtil.clone(aggregate));
    }

    @Override
    public void remove(ID id) {
        cacheMap.remove(id);
    }

}
