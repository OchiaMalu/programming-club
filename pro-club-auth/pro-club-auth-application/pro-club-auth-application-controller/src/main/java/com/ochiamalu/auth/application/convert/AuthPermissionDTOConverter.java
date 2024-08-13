package com.ochiamalu.auth.application.convert;

import com.ochiamalu.auth.application.dto.AuthPermissionDTO;
import com.ochiamalu.auth.domain.entity.AuthPermissionBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 权限dto转换器
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Mapper
public interface AuthPermissionDTOConverter {

    AuthPermissionDTOConverter INSTANCE = Mappers.getMapper(AuthPermissionDTOConverter.class);

    AuthPermissionBO convertDTO2BO(AuthPermissionDTO authPermissionDTO);
}
