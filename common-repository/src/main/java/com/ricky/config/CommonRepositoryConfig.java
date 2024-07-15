package com.ricky.config;

import com.ricky.properties.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ricky
 * @version 1.0
 * @date 2024/7/15
 * @className CommonRepositoryConfig
 * @desc
 */
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class CommonRepositoryConfig {
}
