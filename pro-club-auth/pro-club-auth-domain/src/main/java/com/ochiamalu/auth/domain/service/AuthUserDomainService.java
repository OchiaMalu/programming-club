package com.ochiamalu.auth.domain.service;

import com.ochiamalu.auth.domain.entity.AuthUserBO;

public interface AuthUserDomainService {
    Boolean register(AuthUserBO authUserBO);

    Boolean update(AuthUserBO authUserBO);

    Boolean delete(AuthUserBO authUserBO);

    Boolean changeStatus(AuthUserBO authUserBO);

    Boolean doLogin(String validCode);

    AuthUserBO getUserInfo(AuthUserBO authUserBO);
}
