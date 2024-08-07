package com.ricky.config;

import com.ricky.aop.RateLimiterAop;
import com.ricky.properties.RateLimiterAopProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@Configuration
@EnableConfigurationProperties(RateLimiterAopProperties.class)
public class RateLimiterAopConfig {

    @Bean
    @ConditionalOnMissingBean(RateLimiterAop.class)
    public RateLimiterAop rateLimiterAop(RateLimiterAopProperties properties) {
        return new RateLimiterAop(properties.getPermitsPerSecond(), properties.getTimeout());
    }

}
