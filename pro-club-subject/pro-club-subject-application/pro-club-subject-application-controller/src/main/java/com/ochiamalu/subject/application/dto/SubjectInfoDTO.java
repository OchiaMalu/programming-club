package com.ochiamalu.subject.application.dto;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class SubjectInfoDTO extends PageInfo implements Serializable {
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
    private List<SubjectAnswerDTO> optionList;

    /**
     * 类别id
     */
    private Long categoryId;

    /**
     * 标签id
     */
    private Long labelId;

    /**
     * 标签名称
     */
    private List<String> labelName;

    /**
     * 关键词
     */
    private String keyword;

    /**
     * 题目数量
     */
    private Integer subjectCount;

    /**
     * 创建人昵称
     */
    private String createUser;

    /**
     * 创建人头像
     */
    private String createUserAvatar;

    /**
     * 是否被当前用户点赞
     */
    private Boolean liked;


    /**
     * 当前题目点赞的数量
     */
    private Integer likedCount;

    /**
     * 下一题
     */
    private Long nextSubjectId;

    /**
     * 上一题
     */
    private Long lastSubjectId;
}
