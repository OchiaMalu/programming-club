package com.ochiamalu.subject.domain.convert;

import com.ochiamalu.subject.domain.entity.SubjectAnswerBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MultipleSubjectConverter {
    MultipleSubjectConverter INSTANCE= Mappers.getMapper(MultipleSubjectConverter.class);

    List<SubjectAnswerBO> convertEntityToBoList(List<SubjectMultiple> result);

    SubjectMultiple convertBoToEntity(SubjectAnswerBO option);
}
