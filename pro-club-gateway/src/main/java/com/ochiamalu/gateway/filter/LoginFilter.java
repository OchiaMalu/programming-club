package com.ochiamalu.gateway.filter;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 登录拦截器
 *
 * @author OchiaMalu
 * @date 2024/08/16
 */
@Component
public class LoginFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String url = request.getURI().getPath();
        if ("/auth/user/login".equals(url)) {
            chain.filter(exchange);
        }
        String loginId = (String) StpUtil.getLoginId();
        if (StringUtils.isBlank(loginId)) {
            throw new RuntimeException("未获取到用户信息");
        }
        mutate.header("loginId", loginId);
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
