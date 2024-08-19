package com.ochiamalu.subject.domain.handler.subject;

import com.ochiamalu.subject.common.enums.SubjectInfoTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目类型工厂
 *
 * @author OchiaMalu
 * @date 2024/08/09
 */
@Component
public class SubjectTypeHandlerFactory implements InitializingBean {

    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlerList;

    private Map<SubjectInfoTypeEnum, SubjectTypeHandler> subjectTypeHandlerMap = new HashMap<>();

    public SubjectTypeHandler getSubjectTypeHandler(int subjectType) {
        SubjectInfoTypeEnum subjectInfoTypeEnum = SubjectInfoTypeEnum.getResultCodeEnum(subjectType);
        return subjectTypeHandlerMap.get(subjectInfoTypeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (SubjectTypeHandler subjectTypeHandler : subjectTypeHandlerList) {
            subjectTypeHandlerMap.put(subjectTypeHandler.getHandlerType(), subjectTypeHandler);
        }
    }
}
