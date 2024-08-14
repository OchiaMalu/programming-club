package com.ochiamalu.auth.infra.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochiamalu.auth.infra.basic.entity.AuthUser;

/**
* @author OchiaMalu
* @description 针对表【auth_user(用户信息表)】的数据库操作Service
* @createDate 2024-08-13 15:43:17
*/
public interface AuthUserService extends IService<AuthUser> {

    Boolean changeStatus(AuthUser authUser);

    AuthUser queryByUsername(String openId);
}
