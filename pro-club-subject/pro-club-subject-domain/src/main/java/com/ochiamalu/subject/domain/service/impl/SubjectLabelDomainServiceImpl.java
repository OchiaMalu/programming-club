package com.ochiamalu.subject.domain.service.impl;

import com.ochiamalu.subject.domain.convert.SubjectLabelConverter;
import com.ochiamalu.subject.domain.entity.SubjectLabelBO;
import com.ochiamalu.subject.domain.service.SubjectLabelDomainService;
import com.ochiamalu.subject.infra.basic.entity.SubjectLabel;
import com.ochiamalu.subject.infra.basic.service.SubjectLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBO2Label(subjectLabelBO);
        return subjectLabelService.save(subjectLabel);
    }

    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBO2Label(subjectLabelBO);
        return subjectLabelService.updateById(subjectLabel);
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBO2Label(subjectLabelBO);
        return subjectLabelService.removeById(subjectLabel);
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(String categoryId) {
        List<SubjectLabel> subjectLabelList = subjectLabelService.queryLabelByCategoryId(categoryId);
        return SubjectLabelConverter.INSTANCE
                .convertLabel2BOList(subjectLabelList);
    }
}
