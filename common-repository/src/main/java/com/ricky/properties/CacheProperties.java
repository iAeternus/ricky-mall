package com.ricky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/14
 * @className CacheProperties
 * @desc
 */
@Data
@Component
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {

    private String appName; // 缓存DDD聚合前缀id，防止redis存储对象的名字一致时，对数据进行覆盖，仅redis有效
    private String type; // 缓存方式 map/redis
    private long cacheExpiresTime; // 过期时间
    private long cacheExpiresSize; // 该属性仅对map时有效

    public static final String MAP = "map";
    public static final String REDIS = "redis";

}
