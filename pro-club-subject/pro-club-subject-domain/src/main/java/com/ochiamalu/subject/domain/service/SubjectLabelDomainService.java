package com.ochiamalu.subject.domain.service;

import com.ochiamalu.subject.domain.entity.SubjectLabelBO;

import java.util.List;

public interface SubjectLabelDomainService {
    Boolean add(SubjectLabelBO subjectLabelBO);

    Boolean update(SubjectLabelBO subjectLabelBO);

    Boolean delete(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> queryLabelByCategoryId(String categoryId);
}
