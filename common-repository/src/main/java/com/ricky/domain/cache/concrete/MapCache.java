package com.ricky.domain.cache.concrete;

import com.ricky.domain.cache.Cache;
import com.ricky.marker.Aggregate;
import com.ricky.marker.Identifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className MapCache
 * @desc
 */
@Service
public class MapCache<T extends Aggregate<ID>, ID extends Identifier> extends Cache<T, ID> {

    private final Map<ID, T> cacheMap = new HashMap<>();

    @Override
    public T find(ID id) {
        return cacheMap.get(id);
    }

    @Override
    public void save(ID id, T aggregate) {
        cacheMap.put(id, aggregate);
    }

    @Override
    public void remove(ID id) {
        cacheMap.remove(id);
    }

}
