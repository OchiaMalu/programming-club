package com.ochiamalu.subject.infra.basic.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis配置
 *
 * @author OchiaMalu
 * @date 2024/08/10
 */
@Configuration
public class MybatisConfig {

    /**
     * 创建并配置MybatisPlusInterceptor拦截器。
     * 这个方法没有参数。
     *
     * @return 返回配置好的MybatisPlusInterceptor实例。
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 初始化核心插件
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 添加一个内部拦截器，用于记录所有SQL日志
//        interceptor.addInnerInterceptor(new MybatisPlusAllSqlLog());
        return interceptor;
    }
}
