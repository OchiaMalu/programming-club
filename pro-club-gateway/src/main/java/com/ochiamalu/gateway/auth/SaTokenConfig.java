package com.ochiamalu.gateway.auth;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sa-token配置
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Configuration
public class SaTokenConfig {

    // 注册 Sa-Token全局过滤器
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    System.out.println("SaHolder.getRequest().getUrl() = " + SaHolder.getRequest().getUrl());
                    // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
                    SaRouter.match("/auth/**", "/auth/user/doLogin", r -> StpUtil.checkLogin());
                    SaRouter.match("/oss/**", r -> StpUtil.checkLogin());
                    SaRouter.match("/subject/add", r -> StpUtil.checkPermission("user:add"));
                    SaRouter.match("/subject/**", r -> StpUtil.checkLogin());
                });
    }
}
