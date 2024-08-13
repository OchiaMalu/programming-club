package com.ochiamalu.auth.domain.service;

import com.ochiamalu.auth.domain.entity.AuthRolePermissionBO;

/**
 * 角色权限服务
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
public interface AuthRolePermissionDomainService {
    Boolean add(AuthRolePermissionBO authRolePermissionBO);
}
