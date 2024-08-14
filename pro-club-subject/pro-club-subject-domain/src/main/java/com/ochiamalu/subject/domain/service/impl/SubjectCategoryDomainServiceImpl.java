package com.ochiamalu.subject.domain.service.impl;

import com.ochiamalu.subject.domain.convert.SubjectCategoryConverter;
import com.ochiamalu.subject.domain.convert.SubjectLabelConverter;
import com.ochiamalu.subject.domain.entity.SubjectCategoryBO;
import com.ochiamalu.subject.domain.entity.SubjectLabelBO;
import com.ochiamalu.subject.domain.service.SubjectCategoryDomainService;
import com.ochiamalu.subject.infra.basic.entity.SubjectCategory;
import com.ochiamalu.subject.infra.basic.entity.SubjectLabel;
import com.ochiamalu.subject.infra.basic.entity.SubjectMapping;
import com.ochiamalu.subject.infra.basic.service.SubjectCategoryService;
import com.ochiamalu.subject.infra.basic.service.SubjectLabelService;
import com.ochiamalu.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Override
    public boolean add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBO2Category(subjectCategoryBO);
        return subjectCategoryService.save(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryPrimaryCategory() {
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryPrimaryCategory();
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE
                .convertCategoryList2BO(subjectCategoryList);
        subjectCategoryBOList.forEach((subjectCategoryBO) -> {
            Integer count = subjectCategoryService.countSubject(subjectCategoryBO.getId());
            subjectCategoryBO.setCount(count);
        });
        return subjectCategoryBOList;
    }

    @Override
    public List<SubjectCategoryBO> queryCategoryByPrimary(Long id) {
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategoryByPrimary(id);
        return SubjectCategoryConverter.INSTANCE
                .convertCategoryList2BO(subjectCategoryList);
    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBO2Category(subjectCategoryBO);
        return subjectCategoryService.updateById(subjectCategory);
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBO2Category(subjectCategoryBO);
        return subjectCategoryService.removeById(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBO2Category(subjectCategoryBO);
        List<SubjectCategory> subjectCategoryList = subjectCategoryService
                .listById(subjectCategory.getId());

        List<SubjectCategoryBO> categoryBOList = SubjectCategoryConverter.INSTANCE
                .convertCategoryList2BO(subjectCategoryList);
        categoryBOList.forEach((categoryBO) -> {
            List<SubjectMapping> subjectMappingList = subjectMappingService
                    .queryByCategoryId(categoryBO.getId());
            Set<Long> labelIdList = subjectMappingList.stream()
                    .map(SubjectMapping::getLabelId).collect(Collectors.toSet());
            if (labelIdList.isEmpty()){
                return;
            }
            List<SubjectLabel> subjectLabelList = subjectLabelService.listByIds(labelIdList);
            List<SubjectLabelBO> subjectLabelBOList = SubjectLabelConverter.INSTANCE
                    .convertLabel2BOList(subjectLabelList);
            categoryBO.setLabelList(subjectLabelBOList);
        });
        return categoryBOList;
    }
}
