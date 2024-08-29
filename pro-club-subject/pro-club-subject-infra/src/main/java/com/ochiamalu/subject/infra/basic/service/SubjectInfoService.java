package com.ochiamalu.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochiamalu.subject.infra.basic.entity.SubjectInfo;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_info(题目信息表)】的数据库操作Service
* @createDate 2024-08-09 18:30:00
*/
public interface SubjectInfoService extends IService<SubjectInfo> {

    int countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId);


    List<SubjectInfo> queryPage(SubjectInfo subjectInfo, Long categoryId, Long labelId, int start, Integer pageSize);

    Long querySubjectIdCursor(Long subjectId, Long categoryId, Long labelId, int cursor);
}
