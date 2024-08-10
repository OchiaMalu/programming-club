package com.ochiamalu.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.subject.infra.basic.entity.SubjectBrief;
import com.ochiamalu.subject.infra.basic.mapper.SubjectBriefMapper;
import com.ochiamalu.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author OchiaMalu
* @description 针对表【subject_brief(简答题)】的数据库操作Service实现
* @createDate 2024-08-09 18:33:48
*/
@Service
public class SubjectBriefServiceImpl extends ServiceImpl<SubjectBriefMapper, SubjectBrief>
    implements SubjectBriefService {

    @Resource
    private SubjectBriefMapper subjectBriefMapper;

    @Override
    public SubjectBrief queryByConditions(SubjectBrief subjectBrief) {
        return subjectBriefMapper.selectByConditions(subjectBrief);
    }
}




