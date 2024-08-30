package com.ochiamalu.practice.server.dao;


import com.ochiamalu.practice.server.entity.po.SubjectMultiplePO;

import java.util.List;

public interface SubjectMultipleDao {

    /**
     * 查询题目
     */
    List<SubjectMultiplePO> selectBySubjectId(Long subjectId);

}