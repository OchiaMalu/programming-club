package com.ochiamalu.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 题目答案dto
 *
 * @author OchiaMalu
 * @date 2024/08/09
 */
@Data
public class SubjectAnswerBO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer optionType;

    /**
     * 答案
     */
    private String optionContent;

    /**
     * 是否正确
     */
    private Integer isCorrect;
}
