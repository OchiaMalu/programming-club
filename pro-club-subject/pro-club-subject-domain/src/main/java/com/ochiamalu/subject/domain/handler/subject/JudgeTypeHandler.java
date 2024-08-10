package com.ochiamalu.subject.domain.handler.subject;

import com.ochiamalu.subject.common.enums.SubjectInfoTypreEnum;
import com.ochiamalu.subject.domain.convert.JudgeSubjectConverter;
import com.ochiamalu.subject.domain.entity.SubjectAnswerBO;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.domain.entity.SubjectOptionBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectJudge;
import com.ochiamalu.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 判断题目策略类
 *
 * @author OchiaMalu
 * @date 2024/08/09
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectJudgeService subjectJudgeService;

    @Override
    public SubjectInfoTypreEnum getHandlerType() {
        return SubjectInfoTypreEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectJudge subjectJudge = new SubjectJudge();
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudgeService.save(subjectJudge);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(subjectId);
        List<SubjectJudge> result = subjectJudgeService.queryByConditions(subjectJudge);
        List<SubjectAnswerBO> subjectAnswerBOList = JudgeSubjectConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
