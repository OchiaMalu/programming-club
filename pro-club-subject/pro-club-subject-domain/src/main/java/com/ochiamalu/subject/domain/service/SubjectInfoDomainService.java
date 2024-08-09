package com.ochiamalu.subject.domain.service;

import com.ochiamalu.subject.domain.entity.SubjectInfoBO;

public interface SubjectInfoDomainService {

    /**
     * 新增题目
     *
     * @param subjectInfoBO 题目信息bo
     * @return {@link Boolean }
     */
    void add(SubjectInfoBO subjectInfoBO);
}
