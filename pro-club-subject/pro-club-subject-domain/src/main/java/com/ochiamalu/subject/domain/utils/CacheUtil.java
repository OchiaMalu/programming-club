package com.ochiamalu.subject.domain.utils;

import com.alibaba.fastjson2.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 缓存util
 *
 * @author OchiaMalu
 * @date 2024/08/16
 */
@Component
public class CacheUtil<V> {

    private final Cache<String, String> localCache =
            CacheBuilder.newBuilder()
                    .maximumSize(5000)
                    .expireAfterWrite(10, TimeUnit.SECONDS)
                    .build();

    public List<V> getResult(String key, Class<V> clazz,
                             Function<String, List<V>> function) {
        List<V> resultList = new LinkedList<>();
        String content = localCache.getIfPresent(key);
        if (StringUtils.isNotBlank(content)) {
            resultList = JSON.parseArray(content, clazz);
        } else {
            resultList = function.apply(key);
            if (!CollectionUtils.isEmpty(resultList)) {
                localCache.put(key, JSON.toJSONString(resultList));
            }
        }
        return resultList;
    }
}
