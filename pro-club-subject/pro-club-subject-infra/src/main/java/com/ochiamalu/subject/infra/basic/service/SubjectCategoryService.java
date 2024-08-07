package com.ochiamalu.subject.infra.basic.service;

import com.ochiamalu.subject.infra.basic.entity.SubjectCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_category(题目分类)】的数据库操作Service
* @createDate 2024-08-06 17:46:04
*/
public interface SubjectCategoryService extends IService<SubjectCategory> {

    /**
     * 查询岗位大类
     */
    List<SubjectCategory> queryPrimaryCategory();
}
