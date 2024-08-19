package com.ochiamalu.subject.domain.handler.subject;

import com.ochiamalu.subject.common.enums.SubjectInfoTypeEnum;
import com.ochiamalu.subject.domain.convert.BriefSubjectConverter;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.domain.entity.SubjectOptionBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectBrief;
import com.ochiamalu.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 简答题目策略类
 *
 * @author OchiaMalu
 * @date 2024/08/09
 */
@Component
public class BriefTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = BriefSubjectConverter.INSTANCE
                .convertBO2Brief(subjectInfoBO);
        subjectBrief.setId(null);
        subjectBrief.setSubjectId(subjectInfoBO.getId());
        subjectBriefService.save(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectId);
        SubjectBrief result = subjectBriefService.queryByConditions(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;
    }
}
