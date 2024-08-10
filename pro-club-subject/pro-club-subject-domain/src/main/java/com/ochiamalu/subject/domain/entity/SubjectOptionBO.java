package com.ochiamalu.subject.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目选项bo
 *
 * @author OchiaMalu
 * @date 2024/08/10
 */
@Data
public class SubjectOptionBO implements Serializable {

    private String subjectAnswer;

    private List<SubjectAnswerBO> optionList;
}
