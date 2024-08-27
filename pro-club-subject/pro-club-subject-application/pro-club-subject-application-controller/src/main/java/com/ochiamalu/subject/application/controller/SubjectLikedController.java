package com.ochiamalu.subject.application.controller;

import com.ochiamalu.subject.application.convert.SubjectLikedDTOConverter;
import com.ochiamalu.subject.application.dto.SubjectLikedDTO;
import com.ochiamalu.subject.common.entity.Result;
import com.ochiamalu.subject.common.utils.LoginUtil;
import com.ochiamalu.subject.domain.entity.SubjectLikedBO;
import com.ochiamalu.subject.domain.service.SubjectLikedDomainService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/like")
public class SubjectLikedController {

    @Resource
    private SubjectLikedDomainService subjectLikedDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLikedDTO subjectLikedDTO) {
        SubjectLikedBO subjectLikedBO = SubjectLikedDTOConverter.INSTANCE
                .convertDTO2BO(subjectLikedDTO);
        subjectLikedBO.setLikeUserId(LoginUtil.getLoginId());
        Boolean result = subjectLikedDomainService.add(subjectLikedBO);
        return Result.ok(result);
    }
}
