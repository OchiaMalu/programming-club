package com.ochiamalu.subject.domain.convert;

import com.ochiamalu.subject.domain.entity.SubjectAnswerBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MultipleSubjectConverter {

    MultipleSubjectConverter INSTANCE = Mappers.getMapper(MultipleSubjectConverter.class);

    SubjectMultiple convertBO2Multiple(SubjectAnswerBO subjectAnswerBO);
}
