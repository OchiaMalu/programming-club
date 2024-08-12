package com.ochiamalu.subject.domain.convert;

import com.ochiamalu.subject.domain.entity.SubjectLabelBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConverter {

    SubjectLabelConverter INSTANCE= Mappers.getMapper(SubjectLabelConverter.class);

    SubjectLabel convertBO2Label(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> convertLabel2BOList(List<SubjectLabel> subjectLabelList);

    List<SubjectLabelBO> convertLabel2BoList(List<SubjectLabel> labelList);
}
