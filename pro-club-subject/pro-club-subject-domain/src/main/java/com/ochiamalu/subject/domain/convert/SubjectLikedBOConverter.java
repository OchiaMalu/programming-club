package com.ochiamalu.subject.domain.convert;

import com.ochiamalu.subject.domain.entity.SubjectLikedBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectLiked;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectLikedBOConverter {
    SubjectLikedBOConverter INSTANCE = Mappers.getMapper(SubjectLikedBOConverter.class);

    SubjectLiked convertBO2Entity(SubjectLikedBO subjectLikedBO);
}
