package com.ochiamalu.subject.domain.service;

import com.ochiamalu.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

public interface SubjectCategoryDomainService {
    boolean add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询岗位大类
     */
    List<SubjectCategoryBO> queryPrimaryCategory();

    /**
     * 查询岗位大类下的分类
     */
    List<SubjectCategoryBO> queryCategoryByPrimary(Long id);
}
