package com.ochiamalu.subject.domain.convert;

import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectBrief;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BriefSubjectConverter {

    BriefSubjectConverter INSTANCE= Mappers.getMapper(BriefSubjectConverter.class);

    SubjectBrief convertBO2Brief(SubjectInfoBO subjectInfoBO);
}
