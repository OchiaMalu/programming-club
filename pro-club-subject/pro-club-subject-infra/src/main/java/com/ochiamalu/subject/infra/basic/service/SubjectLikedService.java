package com.ochiamalu.subject.infra.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ochiamalu.subject.infra.basic.entity.SubjectLiked;

/**
 * @author OchiaMalu
 * @description 针对表【subject_liked(题目点赞表)】的数据库操作Service
 * @createDate 2024-08-27 20:19:13
 */
public interface SubjectLikedService extends IService<SubjectLiked> {

    SubjectLiked getSubjectLiked(Long subjectId, String likeUserId);

    Boolean isLiked(Long subjectId, String likeUserId);

    Integer likedCount(Long subjectId);
}
