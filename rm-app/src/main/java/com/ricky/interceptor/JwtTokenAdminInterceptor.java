package com.ricky.interceptor;


import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import com.ricky.config.JwtConfigProperties;
import com.ricky.constants.JwtClaimsConstant;
import com.ricky.context.UserContext;
import com.ricky.utils.JwtUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校验的拦截器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    private final JwtConfigProperties jwtConfigProperties;

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler) {
        // 判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 从请求头中获取令牌
        String token = request.getHeader(jwtConfigProperties.getTokenName());

        if (StrUtil.isBlank(token)) {
            throw new RuntimeException("无token，请重新登录");
        }

        // 校验令牌
        try {
            log.info("jwt校验: {}", token);
            Claims claims = JwtUtils.parseJWT(jwtConfigProperties.getSecretKey(), token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            log.info("当前用户id: {}", userId);
            UserContext.setCurrentId(userId);
            // 通过，放行
            return true;
        } catch (Exception ex) {
            // 不通过，响应 401 状态码
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            Exception ex) {
        // 清理用户
        UserContext.removeCurrentId();
    }

}
