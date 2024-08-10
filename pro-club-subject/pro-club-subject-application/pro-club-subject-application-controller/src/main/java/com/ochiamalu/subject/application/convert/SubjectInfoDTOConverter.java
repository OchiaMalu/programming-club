package com.ochiamalu.subject.application.convert;

import com.ochiamalu.subject.application.dto.SubjectInfoDTO;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectInfoDTOConverter {

    SubjectInfoDTOConverter INSTANCE = Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDTO2BO(SubjectInfoDTO subjectInfoDTO);

    SubjectInfoDTO convertBO2DTO(SubjectInfoBO subjectInfoBO);
}
