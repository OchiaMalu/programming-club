package com.ochiamalu.subject.domain.service.impl;

import com.ochiamalu.subject.domain.convert.SubjectCategoryConverter;
import com.ochiamalu.subject.domain.entity.SubjectCategoryBO;
import com.ochiamalu.subject.domain.service.SubjectCategoryDomainService;
import com.ochiamalu.subject.infra.basic.entity.SubjectCategory;
import com.ochiamalu.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Override
    public boolean add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBO2Category(subjectCategoryBO);
        return subjectCategoryService.save(subjectCategory);
    }
}
