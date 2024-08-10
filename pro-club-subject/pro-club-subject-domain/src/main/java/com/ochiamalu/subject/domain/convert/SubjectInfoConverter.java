package com.ochiamalu.subject.domain.convert;

import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.domain.entity.SubjectOptionBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE = Mappers.getMapper(SubjectInfoConverter.class);

    SubjectInfo convertBO2Info(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> convertInfo2BOList(List<SubjectInfo> subjectInfoList);

    SubjectInfoBO convertOptionAndInfoToBo(SubjectOptionBO optionBO, SubjectInfo subjectInfo);
}
