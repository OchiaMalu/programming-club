package com.ochiamalu.auth.domain.service;

import com.ochiamalu.auth.domain.entity.AuthPermissionBO;

import java.util.List;

/**
 * 用户权限服务
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
public interface AuthPermissionDomainService {
    Boolean add(AuthPermissionBO authPermissionBO);
    Boolean update(AuthPermissionBO authPermissionBO);
    Boolean delete(AuthPermissionBO authPermissionBO);

    List<String> getPermission(String userName);
}
