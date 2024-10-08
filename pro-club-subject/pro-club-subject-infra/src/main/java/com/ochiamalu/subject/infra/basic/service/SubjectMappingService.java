package com.ochiamalu.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochiamalu.subject.infra.basic.entity.SubjectMapping;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_mapping(题目分类关系表)】的数据库操作Service
* @createDate 2024-08-09 19:53:27
*/
public interface SubjectMappingService extends IService<SubjectMapping> {

    List<SubjectMapping> queryBySubjectId(Long subjectId);
    List<SubjectMapping> queryByCategoryId(Long categoryId);
}
