package com.ochiamalu.auth.domain.service.impl;

import com.ochiamalu.auth.domain.entity.AuthRolePermissionBO;
import com.ochiamalu.auth.domain.service.AuthRolePermissionDomainService;
import com.ochiamalu.auth.infra.basic.entity.AuthRolePermission;
import com.ochiamalu.auth.infra.basic.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色权限服务实现
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Service
public class AuthRolePermissionDomainServiceImpl implements AuthRolePermissionDomainService {

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Override
    public Boolean add(AuthRolePermissionBO authRolePermissionBO) {
        authRolePermissionBO.getPermissionIdList().forEach((permissionId) -> {
            AuthRolePermission authRolePermission = new AuthRolePermission();
            authRolePermission.setRoleId(authRolePermissionBO.getRoleId());
            authRolePermission.setPermissionId(permissionId);
            authRolePermissionService.save(authRolePermission);
        });
        return true;
    }
}
