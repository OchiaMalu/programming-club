package com.ochiamalu.auth.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.auth.infra.basic.entity.AuthRole;
import com.ochiamalu.auth.infra.basic.mapper.AuthRoleMapper;
import com.ochiamalu.auth.infra.basic.service.AuthRoleService;
import org.springframework.stereotype.Service;

/**
* @author OchiaMalu
* @description 针对表【auth_role】的数据库操作Service实现
* @createDate 2024-08-13 19:22:20
*/
@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole>
    implements AuthRoleService {

}




