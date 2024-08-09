package com.ochiamalu.subject.domain.handler.subject;

import com.ochiamalu.subject.common.enums.SubjectInfoTypreEnum;
import com.ochiamalu.subject.domain.convert.RadioSubjectConverter;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.infra.basic.entity.SubjectRadio;
import com.ochiamalu.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 单选题目策略类
 *
 * @author OchiaMalu
 * @date 2024/08/09
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypreEnum getHandlerType() {
        return SubjectInfoTypreEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        ArrayList<SubjectRadio> subjectRadioList = new ArrayList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectRadio subjectRadio = RadioSubjectConverter.INSTANCE
                    .convertBO2Radio(option);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadioList.add(subjectRadio);
        });
        subjectRadioService.saveBatch(subjectRadioList);
    }
}
