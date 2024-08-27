package com.ochiamalu.subject.domain.service.impl;

import com.ochiamalu.subject.domain.convert.SubjectLikedBOConverter;
import com.ochiamalu.subject.domain.entity.SubjectLikedBO;
import com.ochiamalu.subject.domain.service.SubjectLikedDomainService;
import com.ochiamalu.subject.infra.basic.entity.SubjectLiked;
import com.ochiamalu.subject.infra.basic.service.SubjectLikedService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 题目喜欢服务实现
 *
 * @author OchiaMalu
 * @date 2024/08/27
 */
@Service
public class SubjectLikedDomainServiceImpl implements SubjectLikedDomainService {

    @Resource
    private SubjectLikedService subjectLikedService;

    @Override
    public Boolean add(SubjectLikedBO subjectLikedBO) {
        String likeUserId = subjectLikedBO.getLikeUserId();
        Long subjectId = subjectLikedBO.getSubjectId();
        SubjectLiked subjectLiked = SubjectLikedBOConverter.INSTANCE
                .convertBO2Entity(subjectLikedBO);
        SubjectLiked subjectLikedInDatabase = subjectLikedService.getSubjectLiked(subjectId, likeUserId);
        if (subjectLikedInDatabase == null) {
            return subjectLikedService.save(subjectLiked);
        }
        subjectLiked.setId(subjectLikedInDatabase.getId());
        return subjectLikedService.updateById(subjectLiked);
    }
}
