package com.ochiamalu.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.subject.infra.basic.entity.SubjectCategory;
import com.ochiamalu.subject.infra.basic.mapper.SubjectCategoryMapper;
import com.ochiamalu.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_category(题目分类)】的数据库操作Service实现
* @createDate 2024-08-06 17:46:04
*/
@Service
public class SubjectCategoryServiceImpl extends ServiceImpl<SubjectCategoryMapper, SubjectCategory>
    implements SubjectCategoryService{

    @Resource
    private SubjectCategoryMapper subjectCategoryMapper;

    @Override
    public List<SubjectCategory> queryPrimaryCategory() {
        return lambdaQuery().eq(SubjectCategory::getParentId, 0).list();
    }

    @Override
    public List<SubjectCategory> queryCategoryByPrimary(Long id) {
        return lambdaQuery().eq(SubjectCategory::getParentId, id).list();
    }

    @Override
    public Integer countSubject(Long id) {
        return subjectCategoryMapper.countCategory(id);
    }
}




