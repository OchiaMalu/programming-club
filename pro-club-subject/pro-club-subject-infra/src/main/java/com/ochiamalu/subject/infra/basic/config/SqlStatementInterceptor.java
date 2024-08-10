package com.ochiamalu.subject.infra.basic.config;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * 定义一个拦截器（Interceptor），用于拦截Executor接口中的update和query方法的执行。
 * 它可以对MappedStatement、Object、RowBounds、ResultHandler、CacheKey和BoundSql等参数进行处理。
 * 通过实现自定义的拦截器逻辑，可以对MyBatis的执行过程进行扩展和定制
 **/
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,
                Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
                Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})})
public class SqlStatementInterceptor implements Interceptor {

    public static final Logger log = LoggerFactory.getLogger("sys-sql");

    /**
     * 拦截方法调用，记录方法执行前后的耗时，以分析SQL执行性能。
     *
     * @param invocation 方法调用的邀请对象，包含调用的上下文信息。
     * @return 返回方法正常执行的返回值。
     * @throws Throwable 如果方法执行过程中发生异常，则抛出。
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long timeConsuming = System.currentTimeMillis() - startTime;
            log.info("执行SQL:{}ms", timeConsuming);
            if (timeConsuming > 999 && timeConsuming < 5000) {
                log.info("执行SQL大于1s:{}ms", timeConsuming);
            } else if (timeConsuming >= 5000 && timeConsuming < 10000) {
                log.info("执行SQL大于5s:{}ms", timeConsuming);
            } else if (timeConsuming >= 10000) {
                log.info("执行SQL大于10s:{}ms", timeConsuming);
            }
        }
    }

    /**
     * 对目标对象进行包装。
     *
     * @param target 目标对象。
     * @return 返回包装后的对象。
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置属性。
     *
     * @param properties 属性配置。
     */
    @Override
    public void setProperties(Properties properties) {

    }
}