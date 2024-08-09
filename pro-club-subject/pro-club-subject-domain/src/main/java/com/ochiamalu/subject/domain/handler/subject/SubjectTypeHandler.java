package com.ochiamalu.subject.domain.handler.subject;

import com.ochiamalu.subject.common.enums.SubjectInfoTypreEnum;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;

public interface SubjectTypeHandler {

    /**
     * 枚举识别
     */
    SubjectInfoTypreEnum getHandlerType();

    /**
     * 题目插入
     */
    void add(SubjectInfoBO subjectInfoBO);
}
