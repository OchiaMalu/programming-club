package com.ochiamalu.auth.domain.convert;

import com.ochiamalu.auth.domain.entity.AuthPermissionBO;
import com.ochiamalu.auth.infra.basic.entity.AuthPermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 权限bo转换器
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Mapper
public interface AuthPermissionBOConverter {
    AuthPermissionBOConverter INSTANCE = Mappers.getMapper(AuthPermissionBOConverter.class);

    AuthPermission convertBO2Entity(AuthPermissionBO authPermissionBO);
}
