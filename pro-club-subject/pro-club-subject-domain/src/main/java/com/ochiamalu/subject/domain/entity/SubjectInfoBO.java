package com.ochiamalu.subject.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ochiamalu.subject.common.entity.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息dto
 *
 * @author OchiaMalu
 * @date 2024/08/09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SubjectInfoBO extends PageInfo implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 题目名称
     */
    private String subjectName;

    /**
     * 题目难度
     */
    private Integer subjectDifficult;

    /**
     * 出题人名
     */
    private String settleName;

    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;

    /**
     * 题目分数
     */
    private Integer subjectScore;

    /**
     * 题目解析
     */
    private String subjectParse;

    /**
     * 题目答案
     */
    private String subjectAnswer;

    /**
     * 分类id
     */
    private List<Long> categoryIds;

    /**
     * 标签id
     */
    private List<Long> labelIds;

    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    private Long categoryId;

    private Long labelId;

    private List<String> labelName;
}
