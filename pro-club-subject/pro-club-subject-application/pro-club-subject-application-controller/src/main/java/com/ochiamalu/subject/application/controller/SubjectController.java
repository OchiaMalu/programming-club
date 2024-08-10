package com.ochiamalu.subject.application.controller;

import com.ochiamalu.subject.application.convert.SubjectAnswerDTOConverter;
import com.ochiamalu.subject.application.convert.SubjectInfoDTOConverter;
import com.ochiamalu.subject.application.dto.SubjectInfoDTO;
import com.ochiamalu.subject.common.entity.PageResult;
import com.ochiamalu.subject.common.entity.Result;
import com.ochiamalu.subject.domain.entity.SubjectAnswerBO;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.domain.service.SubjectInfoDomainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目控制器
 *
 * @author OchiaMalu
 * @date 2024/08/09
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    /**
     * 新增题目
     *
     * @param subjectInfoDTO 主题信息dto
     * @return {@link Result }<{@link Boolean }>
     */
    @PostMapping("/add")
    public Result<Boolean> addSubject(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE
                .convertDTO2BO(subjectInfoDTO);
        List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerDTOConverter.INSTANCE
                .convertDTOList2BO(subjectInfoDTO.getOptionList());
        subjectInfoBO.setOptionList(subjectAnswerBOList);
        subjectInfoDomainService.add(subjectInfoBO);
        return Result.ok();
    }

    @GetMapping("/querySubjectList")
    public Result<PageResult<SubjectInfoBO>> getSubjectPage(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTO2BO(subjectInfoDTO);
        subjectInfoBO.setPageNo(subjectInfoDTO.getPageNo());
        subjectInfoBO.setPageSize(subjectInfoDTO.getPageSize());
        PageResult<SubjectInfoBO> boPageResult = subjectInfoDomainService.getSubjectPage(subjectInfoBO);
        return Result.ok(boPageResult);
    }

    @GetMapping("/querySubjectInfo")
    public Result<SubjectInfoDTO> getSubjectInfo(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDTO2BO(subjectInfoDTO);
        SubjectInfoBO boResult = subjectInfoDomainService.querySubjectInfo(subjectInfoBO);
        SubjectInfoDTO dto = SubjectInfoDTOConverter.INSTANCE.convertBO2DTO(boResult);
        return Result.ok(dto);
    }
}
