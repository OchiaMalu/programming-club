package com.ochiamalu.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.subject.infra.basic.entity.SubjectInfo;
import com.ochiamalu.subject.infra.basic.mapper.SubjectInfoMapper;
import com.ochiamalu.subject.infra.basic.service.SubjectInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_info(题目信息表)】的数据库操作Service实现
* @createDate 2024-08-09 18:30:00
*/
@Service
public class SubjectInfoServiceImpl extends ServiceImpl<SubjectInfoMapper, SubjectInfo>
    implements SubjectInfoService{

    @Resource
    private SubjectInfoMapper subjectInfoMapper;

    @Override
    public int countByCondition(SubjectInfo subjectInfo, Long categoryId, Long labelId) {
        return this.subjectInfoMapper.countByCondition(subjectInfo, categoryId, labelId);
    }

    @Override
    public List<SubjectInfo> queryPage(SubjectInfo subjectInfo, Long categoryId, Long labelId, int start, Integer pageSize) {
        return this.subjectInfoMapper.queryPage(subjectInfo, categoryId, labelId, start, pageSize);
    }

    @Override
    public Long querySubjectIdCursor(Long subjectId, Long categoryId, Long labelId, int cursor) {
        return this.subjectInfoMapper.querySubjectIdCursor(subjectId, categoryId, labelId, cursor);
    }
}




