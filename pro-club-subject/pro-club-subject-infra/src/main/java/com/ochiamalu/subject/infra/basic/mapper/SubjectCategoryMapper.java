package com.ochiamalu.subject.infra.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochiamalu.subject.infra.basic.entity.SubjectCategory;

/**
* @author OchiaMalu
* @description 针对表【subject_category(题目分类)】的数据库操作Mapper
* @createDate 2024-08-06 17:46:04
* @Entity com.ochiamalu.subject.infra.basic.entity.SubjectCategory
*/
public interface SubjectCategoryMapper extends BaseMapper<SubjectCategory> {

    Integer countCategory(Long id);
}




