package com.ricky.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigProperties {

    /**
     * 用户生成jwt令牌相关配置
     */
    private String secretKey;
    private long ttl;
    private String tokenName;

}
