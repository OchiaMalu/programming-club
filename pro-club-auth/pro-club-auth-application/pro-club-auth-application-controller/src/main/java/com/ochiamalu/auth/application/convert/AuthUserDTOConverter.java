package com.ochiamalu.auth.application.convert;

import com.ochiamalu.auth.application.dto.AuthUserDTO;
import com.ochiamalu.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserDTOConverter {

    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);

    AuthUserBO convertDTO2BO(AuthUserDTO authUserDTO);

    AuthUserDTO convertBO2DTO(AuthUserBO userInfo);
}
