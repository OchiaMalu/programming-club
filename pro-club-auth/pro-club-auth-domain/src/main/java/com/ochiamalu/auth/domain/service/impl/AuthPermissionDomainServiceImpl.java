package com.ochiamalu.auth.domain.service.impl;

import com.ochiamalu.auth.domain.convert.AuthPermissionBOConverter;
import com.ochiamalu.auth.domain.entity.AuthPermissionBO;
import com.ochiamalu.auth.domain.service.AuthPermissionDomainService;
import com.ochiamalu.auth.infra.basic.entity.AuthPermission;
import com.ochiamalu.auth.infra.basic.service.AuthPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
