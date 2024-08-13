package com.ochiamalu.auth.domain.service.impl;

import com.ochiamalu.auth.domain.convert.AuthRoleBOConverter;
import com.ochiamalu.auth.domain.entity.AuthRoleBO;
import com.ochiamalu.auth.domain.service.AuthRoleDomainService;
import com.ochiamalu.auth.infra.basic.entity.AuthRole;
import com.ochiamalu.auth.infra.basic.service.AuthRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色服务实现
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Service
public class AuthRoleDomainServiceImpl implements AuthRoleDomainService {

    @Resource
    private AuthRoleService authRoleService;

    @Override
    public Boolean add(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE
                .convertBO2Entity(authRoleBO);
        return authRoleService.save(authRole);
    }

    @Override
    public Boolean update(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE
                .convertBO2Entity(authRoleBO);
        return authRoleService.updateById(authRole);
    }

    @Override
    public Boolean delete(AuthRoleBO authRoleBO) {
        AuthRole authRole = AuthRoleBOConverter.INSTANCE
                .convertBO2Entity(authRoleBO);
        return authRoleService.removeById(authRole);
    }
}
