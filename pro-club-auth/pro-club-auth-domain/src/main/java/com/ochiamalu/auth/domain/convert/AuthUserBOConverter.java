package com.ochiamalu.auth.domain.convert;

import com.ochiamalu.auth.domain.entity.AuthUserBO;
import com.ochiamalu.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserBOConverter {
    AuthUserBOConverter INSTANCE = Mappers.getMapper(AuthUserBOConverter.class);

    AuthUser convertBO2Entity(AuthUserBO authUserBO);

    AuthUserBO convertEntity2BO(AuthUser userInfo);
}
