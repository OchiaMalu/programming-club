package com.ochiamalu.subject.domain.convert;

import com.ochiamalu.subject.domain.entity.SubjectCategoryBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryConverter {

    SubjectCategoryConverter INSTANCE = Mappers.getMapper(SubjectCategoryConverter.class);

    SubjectCategory convertBO2Category(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> convertCategoryList2BO(List<SubjectCategory> subjectCategoryList);
}
