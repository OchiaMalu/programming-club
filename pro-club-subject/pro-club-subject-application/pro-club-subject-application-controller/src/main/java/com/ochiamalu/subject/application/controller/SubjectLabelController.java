package com.ochiamalu.subject.application.controller;

import com.google.common.base.Preconditions;
import com.ochiamalu.subject.application.convert.SubjectLabelDTOConverter;
import com.ochiamalu.subject.application.dto.SubjectLabelDTO;
import com.ochiamalu.subject.common.entity.Result;
import com.ochiamalu.subject.domain.entity.SubjectLabelBO;
import com.ochiamalu.subject.domain.service.SubjectLabelDomainService;
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
@RequestMapping("/subject/label")
public class SubjectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE
                .convertDTO2BO(subjectLabelDTO);
        Boolean result = subjectLabelDomainService.add(subjectLabelBO);
        return Result.ok(result);
    }

    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE
                .convertDTO2BO(subjectLabelDTO);
        Boolean result = subjectLabelDomainService.update(subjectLabelBO);
        return Result.ok(result);
    }

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE
                .convertDTO2BO(subjectLabelDTO);
        Boolean result = subjectLabelDomainService.delete(subjectLabelBO);
        return Result.ok(result);
    }

    @GetMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "分类ID不能为空");
        SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE
                .convertDTO2BO(subjectLabelDTO);
        List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService
                .queryLabelByCategoryId(subjectLabelBO.getCategoryId());
        List<SubjectLabelDTO> subjectLabelDTOList = SubjectLabelDTOConverter.INSTANCE
                .convertBO2DTOList(subjectLabelBOList);
        return Result.ok(subjectLabelDTOList);
    }
}
