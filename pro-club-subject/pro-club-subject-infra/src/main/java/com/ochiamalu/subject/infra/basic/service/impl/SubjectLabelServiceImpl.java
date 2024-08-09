package com.ochiamalu.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.subject.infra.basic.entity.SubjectLabel;
import com.ochiamalu.subject.infra.basic.mapper.SubjectLabelMapper;
import com.ochiamalu.subject.infra.basic.service.SubjectLabelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_label(题目标签表)】的数据库操作Service实现
* @createDate 2024-08-09 15:20:50
*/
@Service
public class SubjectLabelServiceImpl extends ServiceImpl<SubjectLabelMapper, SubjectLabel>
    implements SubjectLabelService{

    @Override
    public List<SubjectLabel> queryLabelByCategoryId(String categoryId) {
        return lambdaQuery().eq(SubjectLabel::getCategoryId, categoryId).list();
    }
}




