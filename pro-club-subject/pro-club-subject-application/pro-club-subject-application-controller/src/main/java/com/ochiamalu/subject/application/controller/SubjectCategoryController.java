package com.ochiamalu.subject.application.controller;

import com.google.common.base.Preconditions;
import com.ochiamalu.subject.application.convert.SubjectCategoryDTOConverter;
import com.ochiamalu.subject.application.dto.SubjectCategoryDTO;
import com.ochiamalu.subject.common.entity.Result;
import com.ochiamalu.subject.domain.entity.SubjectCategoryBO;
import com.ochiamalu.subject.domain.service.SubjectCategoryDomainService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory() {
        List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryPrimaryCategory();
        List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE
                .convertBOList2DTO(subjectCategoryBOList);
        return Result.ok(subjectCategoryDTOList);
    }

    @GetMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分类ID不能为空");
        SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE
                .convertDTO2BO(subjectCategoryDTO);
        List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService
                .queryCategoryByPrimary(subjectCategoryBO.getParentId());
        List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE
                .convertBOList2DTO(subjectCategoryBOList);
        return Result.ok(subjectCategoryDTOList);
    }

    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE
                .convertDTO2BO(subjectCategoryDTO);
        Boolean result = subjectCategoryDomainService.update(subjectCategoryBO);
        return Result.ok(result);
    }

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE
                .convertDTO2BO(subjectCategoryDTO);
        Boolean result = subjectCategoryDomainService.delete(subjectCategoryBO);
        return Result.ok(result);
    }
}
