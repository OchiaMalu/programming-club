package com.ochiamalu.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.subject.infra.basic.entity.SubjectJudge;
import com.ochiamalu.subject.infra.basic.mapper.SubjectJudgeMapper;
import com.ochiamalu.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_judge(判断题)】的数据库操作Service实现
* @createDate 2024-08-09 18:34:05
*/
@Service
public class SubjectJudgeServiceImpl extends ServiceImpl<SubjectJudgeMapper, SubjectJudge>
    implements SubjectJudgeService {

    @Resource
    private SubjectJudgeMapper subjectJudgeMapper;

    @Override
    public List<SubjectJudge> queryByConditions(SubjectJudge subjectJudge) {
        return subjectJudgeMapper.selectByConditions(subjectJudge);
    }
}




