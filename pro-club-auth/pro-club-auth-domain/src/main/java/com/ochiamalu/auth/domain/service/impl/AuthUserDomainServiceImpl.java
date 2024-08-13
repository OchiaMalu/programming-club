package com.ochiamalu.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.ochiamalu.auth.domain.convert.AuthUserBOConverter;
import com.ochiamalu.auth.domain.entity.AuthUserBO;
import com.ochiamalu.auth.domain.service.AuthUserDomainService;
import com.ochiamalu.auth.infra.basic.entity.AuthUser;
import com.ochiamalu.auth.infra.basic.service.AuthUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务实现
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Service
public class AuthUserDomainServiceImpl implements AuthUserDomainService {

    @Resource
    private AuthUserService authUserService;

    private static final String SALT = "ochiamalu";

    @Override
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBO2Entity(authUserBO);
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), SALT));
        return authUserService.save(authUser);
    }

    @Override
    public Boolean update(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBO2Entity(authUserBO);
        return authUserService.updateById(authUser);
    }

    @Override
    public Boolean delete(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBO2Entity(authUserBO);
        return authUserService.removeById(authUser);
    }

    @Override
    public Boolean changeStatus(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBO2Entity(authUserBO);
        return authUserService.changeStatus(authUser);
    }
}
