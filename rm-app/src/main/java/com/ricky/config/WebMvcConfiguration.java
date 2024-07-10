package com.ricky.config;


import com.ricky.interceptor.JwtTokenAdminInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final JwtTokenAdminInterceptor jwtTokenAdminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new HandlerInterceptor() {
        //     @Override
        //     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //         if (!"GET".equalsIgnoreCase(request.getMethod()) || !request.getRequestURI().equals("/favicon.ico")) {
        //             return true;
        //         }
        //         response.setStatus(HttpStatus.NO_CONTENT.value()); // 设置状态码为204 No Content
        //         return false;
        //     }
        // });

        registry.addInterceptor(jwtTokenAdminInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/api/v1/auth/**")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/v2/api-docs/**");
    }

}
