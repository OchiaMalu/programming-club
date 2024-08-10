package com.ochiamalu.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.subject.infra.basic.entity.SubjectMapping;
import com.ochiamalu.subject.infra.basic.mapper.SubjectMappingMapper;
import com.ochiamalu.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OchiaMalu
 * @description 针对表【subject_mapping(题目分类关系表)】的数据库操作Service实现
 * @createDate 2024-08-09 19:53:27
 */
@Service
public class SubjectMappingServiceImpl extends ServiceImpl<SubjectMappingMapper, SubjectMapping>
        implements SubjectMappingService {

    @Override
    public List<SubjectMapping> queryLabelId(Long subjectId) {
        return lambdaQuery().eq(SubjectMapping::getSubjectId, subjectId).list();
    }
}




