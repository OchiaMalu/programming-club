package com.ochiamalu.auth.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.auth.domain.entity.AuthRolePermission;
import com.ochiamalu.auth.domain.mapper.AuthRolePermissionMapper;
import com.ochiamalu.auth.domain.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;

/**
* @author OchiaMalu
* @description 针对表【auth_role_permission(角色权限关联表)】的数据库操作Service实现
* @createDate 2024-08-13 19:22:32
*/
@Service
public class AuthRolePermissionServiceImpl extends ServiceImpl<AuthRolePermissionMapper, AuthRolePermission>
    implements AuthRolePermissionService {

}




