package com.ochiamalu.auth.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.auth.infra.basic.entity.AuthRolePermission;
import com.ochiamalu.auth.infra.basic.mapper.AuthRolePermissionMapper;
import com.ochiamalu.auth.infra.basic.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【auth_role_permission(角色权限关联表)】的数据库操作Service实现
* @createDate 2024-08-13 19:22:32
*/
@Service
public class AuthRolePermissionServiceImpl extends ServiceImpl<AuthRolePermissionMapper, AuthRolePermission>
    implements AuthRolePermissionService {

    @Override
    public List<AuthRolePermission> queryByRoleId(Long roleId) {
        return lambdaQuery().eq(AuthRolePermission::getRoleId, roleId).list();
    }
}




