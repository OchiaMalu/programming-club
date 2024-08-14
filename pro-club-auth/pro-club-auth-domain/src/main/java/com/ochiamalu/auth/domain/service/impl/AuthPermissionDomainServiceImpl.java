package com.ochiamalu.auth.domain.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ochiamalu.auth.domain.convert.AuthPermissionBOConverter;
import com.ochiamalu.auth.domain.entity.AuthPermissionBO;
import com.ochiamalu.auth.domain.service.AuthPermissionDomainService;
import com.ochiamalu.auth.infra.basic.entity.AuthPermission;
import com.ochiamalu.auth.infra.basic.service.AuthPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

import static com.ochiamalu.auth.domain.constants.RedisConstants.AUTH_PERMISSION_PREFIX;

/**
 * 用户权限服务实现
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Service
public class AuthPermissionDomainServiceImpl implements AuthPermissionDomainService {

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Boolean add(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE
                .convertBO2Entity(authPermissionBO);
        return authPermissionService.save(authPermission);
    }

    @Override
    public Boolean update(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE
                .convertBO2Entity(authPermissionBO);
        return authPermissionService.updateById(authPermission);
    }

    @Override
    public Boolean delete(AuthPermissionBO authPermissionBO) {
        AuthPermission authPermission = AuthPermissionBOConverter.INSTANCE
                .convertBO2Entity(authPermissionBO);
        return authPermissionService.removeById(authPermission);
    }

    @Override
    public List<String> getPermission(String userName) {
        String key = AUTH_PERMISSION_PREFIX + userName;
        String permissionStr = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isBlank(permissionStr)) {
            return Collections.emptyList();
        }
        return new Gson().fromJson(permissionStr, new TypeToken<List<String>>() {
        }.getType());
    }
}
