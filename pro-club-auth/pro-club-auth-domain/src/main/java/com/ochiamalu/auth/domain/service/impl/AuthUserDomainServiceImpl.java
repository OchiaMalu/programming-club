package com.ochiamalu.auth.domain.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;
import com.ochiamalu.auth.domain.convert.AuthUserBOConverter;
import com.ochiamalu.auth.domain.entity.AuthUserBO;
import com.ochiamalu.auth.domain.service.AuthUserDomainService;
import com.ochiamalu.auth.infra.basic.entity.AuthPermission;
import com.ochiamalu.auth.infra.basic.entity.AuthRole;
import com.ochiamalu.auth.infra.basic.entity.AuthRolePermission;
import com.ochiamalu.auth.infra.basic.entity.AuthUser;
import com.ochiamalu.auth.infra.basic.entity.AuthUserRole;
import com.ochiamalu.auth.infra.basic.service.AuthPermissionService;
import com.ochiamalu.auth.infra.basic.service.AuthRolePermissionService;
import com.ochiamalu.auth.infra.basic.service.AuthRoleService;
import com.ochiamalu.auth.infra.basic.service.AuthUserRoleService;
import com.ochiamalu.auth.infra.basic.service.AuthUserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

import static com.ochiamalu.auth.common.constants.WxChatMsgConstant.LOGIN_PREFIX;
import static com.ochiamalu.auth.domain.constants.AuthConstant.NORMAL_USER;
import static com.ochiamalu.auth.domain.constants.RedisConstants.AUTH_PERMISSION_PREFIX;
import static com.ochiamalu.auth.domain.constants.RedisConstants.AUTH_ROLE_PREFIX;

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

    @Resource
    private AuthRolePermissionService authRolePermissionService;

    @Resource
    private AuthPermissionService authPermissionService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String SALT = "ochiamalu";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBO2Entity(authUserBO);
//        authUser.setPassword(SaSecureUtil.md5BySalt(authUser.getPassword(), SALT));
        authUserService.save(authUser);

        AuthRole authRole = authRoleService.queryByRoleKey(NORMAL_USER);
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(authUser.getId());
        authUserRole.setRoleId(authRole.getId());
        authUserRoleService.save(authUserRole);

        String roleKey = AUTH_ROLE_PREFIX + authUser.getId();
        LinkedList<String> roleList = new LinkedList<>();
        roleList.add(authRole.getRoleKey());
        stringRedisTemplate.opsForValue().set(roleKey, new Gson().toJson(roleList));

        List<AuthRolePermission> authRolePermissionList = authRolePermissionService
                .queryByRoleId(authRole.getId());
        LinkedList<String> permissionList = new LinkedList<>();
        authRolePermissionList.forEach(authRolePermission -> {
            AuthPermission permission = authPermissionService
                    .getById(authRolePermission.getPermissionId());
            permissionList.add(permission.getPermissionKey());
        });
        String permissionKey = AUTH_PERMISSION_PREFIX + authUser.getId();
        stringRedisTemplate.opsForValue().set(permissionKey, new Gson().toJson(permissionList));
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean doLogin(String validCode) {
        String numKey = LOGIN_PREFIX + validCode;
        String openId = stringRedisTemplate.opsForValue().get(numKey);
        if (openId == null) {
            return false;
        }
        AuthUser user = authUserService.queryByUsername(openId);
        if (user == null) {
            AuthUserBO authUser = new AuthUserBO();
            authUser.setUserName(openId);
            authUser.setNickName(openId);
            this.register(authUser);
        }
        StpUtil.login(openId);
        return true;
    }

    @Override
    public AuthUserBO getUserInfo(AuthUserBO authUserBO) {
        AuthUser userInfo = authUserService.getUserInfo(authUserBO.getUserName());
        return AuthUserBOConverter.INSTANCE
                .convertEntity2BO(userInfo);
    }
}
