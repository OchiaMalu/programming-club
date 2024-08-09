package com.ochiamalu.subject.domain.convert;

import com.ochiamalu.subject.domain.entity.SubjectAnswerBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RadioSubjectConverter {

    RadioSubjectConverter INSTANCE= Mappers.getMapper(RadioSubjectConverter.class);

    SubjectRadio convertBO2Radio(SubjectAnswerBO subjectAnswerBO);
}
