package com.ochiamalu.subject.infra.basic.service;

import com.ochiamalu.subject.common.entity.PageResult;
import com.ochiamalu.subject.infra.basic.entity.SubjectInfoEs;

/**
 * 题目es服务
 *
 * @author OchiaMalu
 * @date 2024/08/19
 */
public interface SubjectEsService {

    boolean addSubject(SubjectInfoEs subjectInfoEs);

    PageResult<SubjectInfoEs> searchSubjects(SubjectInfoEs subjectInfoEs);
}
