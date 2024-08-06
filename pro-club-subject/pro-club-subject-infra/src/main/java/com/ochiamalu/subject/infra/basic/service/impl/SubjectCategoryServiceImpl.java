package com.ochiamalu.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.subject.infra.basic.entity.SubjectCategory;
import com.ochiamalu.subject.infra.basic.service.SubjectCategoryService;
import com.ochiamalu.subject.infra.basic.mapper.SubjectCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author OchiaMalu
* @description 针对表【subject_category(题目分类)】的数据库操作Service实现
* @createDate 2024-08-06 17:46:04
*/
@Service
public class SubjectCategoryServiceImpl extends ServiceImpl<SubjectCategoryMapper, SubjectCategory>
    implements SubjectCategoryService{

}




