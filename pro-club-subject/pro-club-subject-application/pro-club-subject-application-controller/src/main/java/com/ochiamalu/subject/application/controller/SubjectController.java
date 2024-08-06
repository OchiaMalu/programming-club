package com.ochiamalu.subject.application.controller;

import com.ochiamalu.subject.infra.basic.entity.SubjectCategory;
import com.ochiamalu.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 刷题控制器
 *
 * @author OchiaMalu
 * @date 2024/08/06
 */
@RestController
public class SubjectController {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @GetMapping("/test")
    public List<SubjectCategory> test() {
        return subjectCategoryService.list();
    }
}
