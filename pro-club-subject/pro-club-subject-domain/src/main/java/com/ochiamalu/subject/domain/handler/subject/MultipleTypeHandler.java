package com.ochiamalu.subject.domain.handler.subject;

import com.ochiamalu.subject.common.enums.SubjectInfoTypreEnum;
import com.ochiamalu.subject.domain.convert.MultipleSubjectConverter;
import com.ochiamalu.subject.domain.entity.SubjectAnswerBO;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.domain.entity.SubjectOptionBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectMultiple;
import com.ochiamalu.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 多选题目策略类
 *
 * @author OchiaMalu
 * @date 2024/08/09
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypreEnum getHandlerType() {
        return SubjectInfoTypreEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectMultiple> subjectMultipleList = new LinkedList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectMultiple subjectMultiple = MultipleSubjectConverter.INSTANCE.convertBoToEntity(option);
            subjectMultiple.setSubjectId(subjectInfoBO.getId());
            subjectMultipleList.add(subjectMultiple);
        });
        subjectMultipleService.saveBatch(subjectMultipleList);
    }

    @Override
    public SubjectOptionBO query(Long subjectId) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(subjectId);
        List<SubjectMultiple> result = subjectMultipleService.queryByConditions(subjectMultiple);
        List<SubjectAnswerBO> subjectAnswerBOList = MultipleSubjectConverter.INSTANCE.convertEntityToBoList(result);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOList);
        return subjectOptionBO;
    }
}
