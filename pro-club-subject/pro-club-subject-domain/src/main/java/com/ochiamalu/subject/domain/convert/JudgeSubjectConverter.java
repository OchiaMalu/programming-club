package com.ochiamalu.subject.domain.convert;

import com.ochiamalu.subject.domain.entity.SubjectAnswerBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JudgeSubjectConverter {
    JudgeSubjectConverter INSTANCE= Mappers.getMapper(JudgeSubjectConverter.class);

    List<SubjectAnswerBO> convertEntityToBoList(List<SubjectJudge> result);
}
