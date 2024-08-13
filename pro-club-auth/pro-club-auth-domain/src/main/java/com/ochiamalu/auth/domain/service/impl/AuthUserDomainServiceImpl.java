package com.ochiamalu.auth.domain.service.impl;

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

    @Override
    public Boolean register(AuthUserBO authUserBO) {
        AuthUser authUser = AuthUserBOConverter.INSTANCE.convertBO2Entity(authUserBO);
        return authUserService.save(authUser);
    }
}
