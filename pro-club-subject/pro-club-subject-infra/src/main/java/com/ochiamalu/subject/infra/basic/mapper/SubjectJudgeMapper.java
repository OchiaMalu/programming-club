package com.ochiamalu.subject.infra.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochiamalu.subject.infra.basic.entity.SubjectJudge;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_judge(判断题)】的数据库操作Mapper
* @createDate 2024-08-09 18:34:05
* @Entity .entity.SubjectJudge
*/
public interface SubjectJudgeMapper extends BaseMapper<SubjectJudge> {

    List<SubjectJudge> selectByConditions(SubjectJudge subjectJudge);
}




