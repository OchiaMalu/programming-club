package com.ochiamalu.subject.infra.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ochiamalu.subject.common.enums.SubjectLikedStatusEnum;
import com.ochiamalu.subject.infra.basic.entity.SubjectLiked;
import com.ochiamalu.subject.infra.basic.mapper.SubjectLikedMapper;
import com.ochiamalu.subject.infra.basic.service.SubjectLikedService;
import org.springframework.stereotype.Service;

/**
 * @author OchiaMalu
 * @description 针对表【subject_liked(题目点赞表)】的数据库操作Service实现
 * @createDate 2024-08-27 20:19:13
 */
@Service
public class SubjectLikedServiceImpl extends ServiceImpl<SubjectLikedMapper, SubjectLiked>
        implements SubjectLikedService {

    @Override
    public SubjectLiked getSubjectLiked(Long subjectId, String likeUserId) {
        return lambdaQuery().eq(SubjectLiked::getSubjectId, subjectId)
                .eq(SubjectLiked::getLikeUserId, likeUserId).one();
    }

    @Override
    public Boolean isLiked(Long subjectId, String likeUserId) {
        SubjectLiked subjectLiked = lambdaQuery().eq(SubjectLiked::getSubjectId, subjectId)
                .eq(SubjectLiked::getLikeUserId, likeUserId)
                .one();
        if (subjectLiked == null) {
            return false;
        }
        return subjectLiked.getStatus().equals(SubjectLikedStatusEnum.LIKED.getCode());
    }

    @Override
    public Integer likedCount(Long subjectId) {
        return Math.toIntExact(lambdaQuery().eq(SubjectLiked::getSubjectId, subjectId)
                .count());
    }
}




