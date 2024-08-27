package com.ochiamalu.subject.domain.service;

import com.ochiamalu.subject.common.entity.PageResult;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectInfoEs;

import java.util.List;

public interface SubjectInfoDomainService {

    /**
     * 新增题目
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 题目分页
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    /**
     * 查询题目详情
     */
    SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO);

    PageResult<SubjectInfoEs> searchSubjects(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> getContributeList();
}
