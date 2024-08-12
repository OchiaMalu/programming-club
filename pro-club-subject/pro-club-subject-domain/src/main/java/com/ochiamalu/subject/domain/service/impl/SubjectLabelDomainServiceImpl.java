package com.ochiamalu.subject.domain.service.impl;

import com.ochiamalu.subject.common.enums.CategoryTypeEnum;
import com.ochiamalu.subject.domain.convert.SubjectLabelConverter;
import com.ochiamalu.subject.domain.entity.SubjectLabelBO;
import com.ochiamalu.subject.domain.service.SubjectLabelDomainService;
import com.ochiamalu.subject.infra.basic.entity.SubjectCategory;
import com.ochiamalu.subject.infra.basic.entity.SubjectLabel;
import com.ochiamalu.subject.infra.basic.entity.SubjectMapping;
import com.ochiamalu.subject.infra.basic.service.SubjectCategoryService;
import com.ochiamalu.subject.infra.basic.service.SubjectLabelService;
import com.ochiamalu.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBO2Label(subjectLabelBO);
        return subjectLabelService.save(subjectLabel);
    }

    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBO2Label(subjectLabelBO);
        return subjectLabelService.updateById(subjectLabel);
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBO2Label(subjectLabelBO);
        return subjectLabelService.removeById(subjectLabel);
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(Long categoryId) {
        //如果当前分类是1级分类，则查询所有标签
        SubjectCategory subjectCategory = subjectCategoryService.getById(categoryId);
        if(CategoryTypeEnum.PRIMARY.getCode() == subjectCategory.getCategoryType()){
            List<SubjectLabel> labelList = subjectLabelService.queryLabelByCategoryId(categoryId);
            return SubjectLabelConverter.INSTANCE.convertLabel2BoList(labelList);
        }
        List<SubjectMapping> mappingList = subjectMappingService.queryByCategoryId(categoryId);
        if (CollectionUtils.isEmpty(mappingList)) {
            return Collections.emptyList();
        }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<SubjectLabelBO> boList = new LinkedList<>();
        labelList.forEach(label -> {
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setId(label.getId());
            bo.setLabelName(label.getLabelName());
            bo.setCategoryId(categoryId);
            bo.setSortNum(label.getSortNum());
            boList.add(bo);
        });
        return boList;
    }
}
