package com.ochiamalu.auth.domain.service;

import com.ochiamalu.auth.domain.entity.AuthUserBO;

public interface AuthUserDomainService {
    Boolean register(AuthUserBO authUserBO);
}
