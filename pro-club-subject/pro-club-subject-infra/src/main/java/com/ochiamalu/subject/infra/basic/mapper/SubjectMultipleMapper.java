package com.ochiamalu.subject.infra.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochiamalu.subject.infra.basic.entity.SubjectMultiple;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_multiple(多选题信息表)】的数据库操作Mapper
* @createDate 2024-08-09 18:34:49
* @Entity .entity.SubjectMultiple
*/
public interface SubjectMultipleMapper extends BaseMapper<SubjectMultiple> {

    List<SubjectMultiple> selectByConditions(SubjectMultiple subjectMultiple);

}




