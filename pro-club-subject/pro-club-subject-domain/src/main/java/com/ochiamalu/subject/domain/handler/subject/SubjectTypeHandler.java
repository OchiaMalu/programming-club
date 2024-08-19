package com.ochiamalu.subject.domain.handler.subject;

import com.ochiamalu.subject.common.enums.SubjectInfoTypeEnum;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.domain.entity.SubjectOptionBO;

public interface SubjectTypeHandler {

    /**
     * 枚举识别
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 题目插入
     */
    void add(SubjectInfoBO subjectInfoBO);

    /**
     * 题目查询
     */
    SubjectOptionBO query(Long subjectId);
}
