package com.ochiamalu.auth.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.auth.infra.basic.entity.AuthPermission;
import com.ochiamalu.auth.infra.basic.mapper.AuthPermissionMapper;
import com.ochiamalu.auth.infra.basic.service.AuthPermissionService;
import org.springframework.stereotype.Service;

/**
* @author OchiaMalu
* @description 针对表【auth_permission】的数据库操作Service实现
* @createDate 2024-08-13 19:22:27
*/
@Service
public class AuthPermissionServiceImpl extends ServiceImpl<AuthPermissionMapper, AuthPermission>
    implements AuthPermissionService {

}




