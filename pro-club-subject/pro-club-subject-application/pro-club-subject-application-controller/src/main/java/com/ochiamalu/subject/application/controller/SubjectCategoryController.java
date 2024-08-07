package com.ochiamalu.subject.application.controller;

import com.ochiamalu.subject.application.convert.SubjectCategoryDTOConverter;
import com.ochiamalu.subject.application.dto.SubjectCategoryDTO;
import com.ochiamalu.subject.common.entity.Result;
import com.ochiamalu.subject.domain.entity.SubjectCategoryBO;
import com.ochiamalu.subject.domain.service.SubjectCategoryDomainService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE
                .convertDTO2BO(subjectCategoryDTO);
        boolean added = subjectCategoryDomainService.add(subjectCategoryBO);
        if (added) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }
}
