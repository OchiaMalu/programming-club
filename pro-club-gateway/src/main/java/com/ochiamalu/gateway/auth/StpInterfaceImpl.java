package com.ochiamalu.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static com.ochiamalu.gateway.constants.RedisConstants.AUTH_PERMISSION_PREFIX;
import static com.ochiamalu.gateway.constants.RedisConstants.AUTH_ROLE_PREFIX;

/**
 * stp接口实现
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return getAuth(loginId, AUTH_PERMISSION_PREFIX);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getAuth(loginId, AUTH_ROLE_PREFIX);
    }

    private List<String> getAuth(Object loginId, String prefix) {
        String authKey = prefix + loginId.toString();
        String authValue = stringRedisTemplate.opsForValue().get(authKey);
        if (StringUtils.isBlank(authValue)) {
            return Collections.emptyList();
        }
        return new Gson().fromJson(authValue, new TypeToken<List<String>>() {
        }.getType());
    }
}
