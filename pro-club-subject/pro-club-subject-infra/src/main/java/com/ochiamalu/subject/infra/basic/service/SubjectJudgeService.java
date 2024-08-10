package com.ochiamalu.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochiamalu.subject.infra.basic.entity.SubjectJudge;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_judge(判断题)】的数据库操作Service
* @createDate 2024-08-09 18:34:05
*/
public interface SubjectJudgeService extends IService<SubjectJudge> {

    List<SubjectJudge> queryByConditions(SubjectJudge subjectJudge);
}
