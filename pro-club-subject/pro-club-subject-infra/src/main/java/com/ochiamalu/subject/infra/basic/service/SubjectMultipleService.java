package com.ochiamalu.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochiamalu.subject.infra.basic.entity.SubjectMultiple;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_multiple(多选题信息表)】的数据库操作Service
* @createDate 2024-08-09 18:34:49
*/
public interface SubjectMultipleService extends IService<SubjectMultiple> {

    List<SubjectMultiple> queryByConditions(SubjectMultiple subjectMultiple);
}
