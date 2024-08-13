package com.ochiamalu.auth.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.auth.infra.basic.entity.AuthUserRole;
import com.ochiamalu.auth.infra.basic.mapper.AuthUserRoleMapper;
import com.ochiamalu.auth.infra.basic.service.AuthUserRoleService;
import org.springframework.stereotype.Service;

/**
* @author OchiaMalu
* @description 针对表【auth_user_role(用户角色表)】的数据库操作Service实现
* @createDate 2024-08-13 19:22:42
*/
@Service
public class AuthUserRoleServiceImpl extends ServiceImpl<AuthUserRoleMapper, AuthUserRole>
    implements AuthUserRoleService {

}




