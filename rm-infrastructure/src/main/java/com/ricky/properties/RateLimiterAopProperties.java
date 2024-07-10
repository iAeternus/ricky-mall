package com.ricky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "rate-limiter", ignoreInvalidFields = true)
public class RateLimiterAopProperties {

    /**
     * 最大调用次数
     */
    private double permitsPerSecond;
    /**
     * 超时等待时间
     */
    private long timeout;

}
