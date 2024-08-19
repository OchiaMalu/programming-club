package com.ochiamalu.subject.infra.basic.entity;

import com.ochiamalu.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 题目es信息
 *
 * @author OchiaMalu
 * @date 2024/08/19
 */
@Data
public class SubjectInfoEs extends PageInfo implements Serializable {

    /**
     * 题目id
     */
    private Long subjectId;

    /**
     * 文档id
     */
    private Long docId;

    /**
     * 题目名称
     */
    private String subjectName;

    /**
     * 题目回答
     */
    private String subjectAnswer;

    /**
     * 题目类型
     */
    private Integer subjectType;

    /**
     * 关键词
     */
    private String keyWord;

    /**
     * 分数
     */
    private BigDecimal score;

    /**
     * 创造用户
     */
    private String createUser;

    /**
     * 创造时间
     */
    private Long createTime;

}
