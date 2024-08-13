package com.ochiamalu.auth.domain.service;

import com.ochiamalu.auth.domain.entity.AuthRoleBO;

/**
 * 角色服务
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
public interface AuthRoleDomainService {
    Boolean add(AuthRoleBO authRoleBO);

    Boolean update(AuthRoleBO authRoleBO);

    Boolean delete(AuthRoleBO authRoleBO);
}
