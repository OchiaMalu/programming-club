package com.ochiamalu.subject.domain.service;

import com.ochiamalu.subject.domain.entity.SubjectLikedBO;

/**
 * 题目喜欢服务
 *
 * @author OchiaMalu
 * @date 2024/08/27
 */
public interface SubjectLikedDomainService {
    Boolean add(SubjectLikedBO subjectLikedBO);
}
