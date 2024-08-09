package com.ochiamalu.subject.domain.service.impl;

import com.ochiamalu.subject.domain.convert.SubjectInfoConverter;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.domain.handler.subject.SubjectTypeHandler;
import com.ochiamalu.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.ochiamalu.subject.domain.service.SubjectInfoDomainService;
import com.ochiamalu.subject.infra.basic.entity.SubjectInfo;
import com.ochiamalu.subject.infra.basic.entity.SubjectMapping;
import com.ochiamalu.subject.infra.basic.service.SubjectInfoService;
import com.ochiamalu.subject.infra.basic.service.SubjectMappingService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 题目服务实现
 *
 * @author OchiaMalu
 * @date 2024/08/09
 */
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE
                .convertBO2Info(subjectInfoBO);
        subjectInfoService.save(subjectInfo);
        SubjectTypeHandler subjectTypeHandler = subjectTypeHandlerFactory
                .getSubjectTypeHandler(subjectInfo.getSubjectType());
        subjectTypeHandler.add(subjectInfoBO);

        ArrayList<SubjectMapping> subjectMappingList = getSubjectMappingList(subjectInfoBO);
        subjectMappingService.saveBatch(subjectMappingList);
    }

    private static @NotNull ArrayList<SubjectMapping> getSubjectMappingList(SubjectInfoBO subjectInfoBO) {
        ArrayList<SubjectMapping> subjectMappingList = new ArrayList<>();
        List<Long> categoryIds = subjectInfoBO.getCategoryIds();
        List<Long> labelIds = subjectInfoBO.getLabelIds();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfoBO.getId());
                subjectMapping.setCategoryId(categoryId);
                subjectMapping.setLabelId(labelId);
                subjectMappingList.add(subjectMapping);
            });
        });
        return subjectMappingList;
    }
}
