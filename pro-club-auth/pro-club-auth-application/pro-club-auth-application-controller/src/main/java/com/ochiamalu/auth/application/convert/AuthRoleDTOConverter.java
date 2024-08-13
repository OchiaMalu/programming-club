package com.ochiamalu.auth.application.convert;

import com.ochiamalu.auth.application.dto.AuthRoleDTO;
import com.ochiamalu.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 角色dto转换器
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Mapper
public interface AuthRoleDTOConverter {

    AuthRoleDTOConverter INSTANCE = Mappers.getMapper(AuthRoleDTOConverter.class);

    AuthRoleBO convertDTO2BO(AuthRoleDTO authRoleDTO);
}
