package com.ochiamalu.subject.application.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目喜欢dto
 *
 * @author OchiaMalu
 * @date 2024/08/27
 */
@Data
public class SubjectLikedDTO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 题目id
     */
    private Long subjectId;

    /**
     * 点赞人id
     */
    private String likeUserId;

    /**
     * 点赞状态 1点赞 0不点赞
     */
    private Integer status;
}