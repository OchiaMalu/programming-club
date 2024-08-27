package com.ochiamalu.subject.application.convert;

import com.ochiamalu.subject.application.dto.SubjectLikedDTO;
import com.ochiamalu.subject.domain.entity.SubjectLikedBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectLikedDTOConverter {
    SubjectLikedDTOConverter INSTANCE = Mappers.getMapper(SubjectLikedDTOConverter.class);

    SubjectLikedBO convertDTO2BO(SubjectLikedDTO subjectLikedDTO);
}
