package com.ochiamalu.auth.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.auth.infra.basic.entity.AuthUser;
import com.ochiamalu.auth.infra.basic.mapper.AuthUserMapper;
import com.ochiamalu.auth.infra.basic.service.AuthUserService;
import org.springframework.stereotype.Service;

/**
 * @author OchiaMalu
 * @description 针对表【auth_user(用户信息表)】的数据库操作Service实现
 * @createDate 2024-08-13 15:43:17
 */
@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser>
        implements AuthUserService {

    @Override
    public Boolean changeStatus(AuthUser authUser) {
        return lambdaUpdate().eq(AuthUser::getId, authUser.getId())
                .set(AuthUser::getStatus, authUser.getStatus())
                .update();
    }

    @Override
    public AuthUser queryByUsername(String openId) {
        return lambdaQuery().eq(AuthUser::getUserName, openId).one();
    }

    @Override
    public AuthUser getUserInfo(String userName) {
        return lambdaQuery().eq(AuthUser::getUserName, userName).one();
    }
}




