package com.ochiamalu.subject.application.convert;

import com.ochiamalu.subject.application.dto.SubjectAnswerDTO;
import com.ochiamalu.subject.domain.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConverter {

    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);
    
    List<SubjectAnswerBO> convertDTOList2BO(List<SubjectAnswerDTO> dtoList);
}
