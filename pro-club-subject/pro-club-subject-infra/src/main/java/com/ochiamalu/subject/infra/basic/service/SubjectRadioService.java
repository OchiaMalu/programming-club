package com.ochiamalu.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochiamalu.subject.infra.basic.entity.SubjectRadio;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_radio(单选题信息表)】的数据库操作Service
* @createDate 2024-08-09 18:34:58
*/
public interface SubjectRadioService extends IService<SubjectRadio> {

    List<SubjectRadio> queryByConditions(SubjectRadio subjectRadio);
}
