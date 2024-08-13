package com.ochiamalu.auth.application.convert;

import com.ochiamalu.auth.application.dto.AuthRolePermissionDTO;
import com.ochiamalu.auth.domain.entity.AuthRolePermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 权限dto转换器
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Mapper
public interface AuthRolePermissionDTOConverter {

    AuthRolePermissionDTOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionDTOConverter.class);

    AuthRolePermissionBO convertDTO2BO(AuthRolePermissionDTO authRolePermissionDTO);
}
