package com.ochiamalu.auth.domain.convert;

import com.ochiamalu.auth.domain.entity.AuthRolePermissionBO;
import com.ochiamalu.auth.infra.basic.entity.AuthRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色bo转换器
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Mapper
public interface AuthRolePermissionBOConverter {
    AuthRolePermissionBOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionBOConverter.class);

    AuthRolePermission convertBO2Entity(AuthRolePermissionBO authRolePermissionBO);
}
