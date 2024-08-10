package com.ochiamalu.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochiamalu.subject.infra.basic.entity.SubjectLabel;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_label(题目标签表)】的数据库操作Service
* @createDate 2024-08-09 15:20:50
*/
public interface SubjectLabelService extends IService<SubjectLabel> {

    List<SubjectLabel> queryLabelByCategoryId(String categoryId);

    List<SubjectLabel> batchQueryById(List<Long> labelIds);
}
