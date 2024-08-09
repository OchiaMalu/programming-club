package com.ochiamalu.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.subject.infra.basic.entity.SubjectInfo;
import com.ochiamalu.subject.infra.basic.mapper.SubjectInfoMapper;
import com.ochiamalu.subject.infra.basic.service.SubjectInfoService;
import org.springframework.stereotype.Service;

/**
* @author OchiaMalu
* @description 针对表【subject_info(题目信息表)】的数据库操作Service实现
* @createDate 2024-08-09 18:30:00
*/
@Service
public class SubjectInfoServiceImpl extends ServiceImpl<SubjectInfoMapper, SubjectInfo>
    implements SubjectInfoService{

}




