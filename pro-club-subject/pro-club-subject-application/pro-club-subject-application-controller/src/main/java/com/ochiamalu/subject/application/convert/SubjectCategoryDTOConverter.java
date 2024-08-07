package com.ochiamalu.subject.application.convert;

import com.ochiamalu.subject.application.dto.SubjectCategoryDTO;
import com.ochiamalu.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {

    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertDTO2BO(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convertBOList2DTO(List<SubjectCategoryBO> subjectCategoryBOList);
}
