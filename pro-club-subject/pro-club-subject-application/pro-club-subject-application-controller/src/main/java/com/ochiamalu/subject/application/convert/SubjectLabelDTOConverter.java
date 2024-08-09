package com.ochiamalu.subject.application.convert;

import com.ochiamalu.subject.application.dto.SubjectLabelDTO;
import com.ochiamalu.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelDTOConverter {

    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDTO2BO(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertBO2DTOList(List<SubjectLabelBO> subjectLabelBOList);
}
