package com.ochiamalu.subject.domain.service.impl;

import com.ochiamalu.subject.common.entity.PageResult;
import com.ochiamalu.subject.domain.convert.SubjectInfoConverter;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.domain.entity.SubjectOptionBO;
import com.ochiamalu.subject.domain.handler.subject.SubjectTypeHandler;
import com.ochiamalu.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.ochiamalu.subject.domain.service.SubjectInfoDomainService;
import com.ochiamalu.subject.infra.basic.entity.SubjectInfo;
import com.ochiamalu.subject.infra.basic.entity.SubjectLabel;
import com.ochiamalu.subject.infra.basic.entity.SubjectMapping;
import com.ochiamalu.subject.infra.basic.service.SubjectInfoService;
import com.ochiamalu.subject.infra.basic.service.SubjectLabelService;
import com.ochiamalu.subject.infra.basic.service.SubjectMappingService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE
                .convertBO2Info(subjectInfoBO);
        subjectInfoService.save(subjectInfo);
        subjectInfoBO.setId(subjectInfo.getId());
        SubjectTypeHandler subjectTypeHandler = subjectTypeHandlerFactory
                .getSubjectTypeHandler(subjectInfo.getSubjectType());
        subjectTypeHandler.add(subjectInfoBO);

        ArrayList<SubjectMapping> subjectMappingList = getSubjectMappingList(subjectInfoBO);
        subjectMappingService.saveBatch(subjectMappingList);
    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBO2Info(subjectInfoBO);
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId()
                , subjectInfoBO.getLabelId());
        if (count == 0) {
            return pageResult;
        }
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId()
                , subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOList = SubjectInfoConverter.INSTANCE.convertInfo2BOList(subjectInfoList);
        subjectInfoBOList.forEach(info -> {
            List<SubjectMapping> mappingList = subjectMappingService.queryBySubjectId(info.getId());
            getLabelName(info, mappingList);
        });
        pageResult.setRecords(subjectInfoBOList);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.getById(subjectInfoBO.getId());
        SubjectTypeHandler handler = subjectTypeHandlerFactory
                .getSubjectTypeHandler(subjectInfo.getSubjectType());
        SubjectOptionBO optionBO = handler.query(subjectInfo.getId());
        SubjectInfoBO bo = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBo(optionBO, subjectInfo);
        List<SubjectMapping> mappingList = subjectMappingService.queryBySubjectId(subjectInfo.getId());
        getLabelName(bo, mappingList);
        return bo;
    }

    private void getLabelName(SubjectInfoBO bo, List<SubjectMapping> mappingList) {
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNameList = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        bo.setLabelName(labelNameList);
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
