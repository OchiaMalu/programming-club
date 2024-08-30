package com.ochiamalu.practice.server.config.context;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录上下文对象
 *
 * @author OchiaMalu
 * @date 2024/08/16
 */

public class LoginContextHolder {

    private static final InheritableThreadLocal<Map<String, Object>> THREAD_LOCAL
            = new InheritableThreadLocal<>();

    public static Map<String, Object> getThreadLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (Objects.isNull(map)) {
            map = new ConcurrentHashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void set(String key, Object value) {
        Map<String, Object> map = getThreadLocalMap();
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = getThreadLocalMap();
        return map.get(key);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    public static String getLoginId() {
        return (String) getThreadLocalMap().get("loginId");
    }
}
