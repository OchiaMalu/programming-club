package com.ochiamalu.auth.domain.convert;

import com.ochiamalu.auth.domain.entity.AuthRoleBO;
import com.ochiamalu.auth.infra.basic.entity.AuthRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色bo转换器
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Mapper
public interface AuthRoleBOConverter {
    AuthRoleBOConverter INSTANCE = Mappers.getMapper(AuthRoleBOConverter.class);

    AuthRole convertBO2Entity(AuthRoleBO authRoleBO);
}
