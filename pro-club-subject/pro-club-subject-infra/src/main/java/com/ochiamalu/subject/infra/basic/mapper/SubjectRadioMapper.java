package com.ochiamalu.subject.infra.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochiamalu.subject.infra.basic.entity.SubjectRadio;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_radio(单选题信息表)】的数据库操作Mapper
* @createDate 2024-08-09 18:34:58
* @Entity .entity.SubjectRadio
*/
public interface SubjectRadioMapper extends BaseMapper<SubjectRadio> {

    List<SubjectRadio> selectByConditions(SubjectRadio subjectRadio);

}




