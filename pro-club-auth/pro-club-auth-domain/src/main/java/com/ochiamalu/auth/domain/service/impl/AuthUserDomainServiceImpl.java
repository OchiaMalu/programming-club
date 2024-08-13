package com.ochiamalu.auth.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.ochiamalu.auth.domain.convert.AuthUserBOConverter;
import com.ochiamalu.auth.domain.entity.AuthUserBO;
import com.ochiamalu.auth.domain.service.AuthUserDomainService;
import com.ochiamalu.auth.infra.basic.entity.AuthRole;
import com.ochiamalu.auth.infra.basic.entity.AuthUser;
import com.ochiamalu.auth.infra.basic.entity.AuthUserRole;
import com.ochiamalu.auth.infra.basic.service.AuthRoleService;
import com.ochiamalu.auth.infra.basic.service.AuthUserRoleService;
import com.ochiamalu.auth.infra.basic.service.AuthUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ochiamalu.auth.domain.constants.AuthConstant.NORMAL_USER;

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

    @Resource
    private AuthRoleService authRoleService;

    @Resource
    private AuthUserRoleService authUserRoleService;

    private static final String SALT = "ochiamalu";

    @Override
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBO2Entity(authUserBO);
        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), SALT));
        authUserService.save(authUser);
        AuthRole authRole = authRoleService.queryByRoleKey(NORMAL_USER);
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(authUser.getId());
        authUserRole.setRoleId(authRole.getId());
        authUserRoleService.save(authUserRole);
        return true;
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
