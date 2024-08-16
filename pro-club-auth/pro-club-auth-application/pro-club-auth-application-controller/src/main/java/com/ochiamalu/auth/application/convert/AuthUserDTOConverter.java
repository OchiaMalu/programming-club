package com.ochiamalu.auth.application.convert;

import com.ochiamalu.auth.domain.entity.AuthUserBO;
import com.ochiamalu.auth.entity.AuthUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserDTOConverter {

    AuthUserDTOConverter INSTANCE = Mappers.getMapper(AuthUserDTOConverter.class);

    AuthUserBO convertDTO2BO(AuthUserDTO authUserDTO);

    AuthUserDTO convertBO2DTO(AuthUserBO userInfo);
}
