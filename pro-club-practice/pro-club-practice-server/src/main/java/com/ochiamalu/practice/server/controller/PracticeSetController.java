package com.ochiamalu.practice.server.controller;

import com.alibaba.fastjson.JSON;
import com.ochiamalu.practice.common.PageResult;
import com.ochiamalu.practice.common.Result;
import com.ochiamalu.practice.req.GetPracticeSubjectListReq;
import com.ochiamalu.practice.req.GetPracticeSubjectReq;
import com.ochiamalu.practice.req.GetPracticeSubjectsReq;
import com.ochiamalu.practice.req.GetPreSetReq;
import com.ochiamalu.practice.req.GetUnCompletePracticeReq;
import com.ochiamalu.practice.server.entity.dto.PracticeSetDTO;
import com.ochiamalu.practice.server.entity.dto.PracticeSubjectDTO;
import com.ochiamalu.practice.server.service.PracticeSetService;
import com.ochiamalu.practice.vo.PracticeSetVO;
import com.ochiamalu.practice.vo.PracticeSubjectListVO;
import com.ochiamalu.practice.vo.PracticeSubjectVO;
import com.ochiamalu.practice.vo.SpecialPracticeVO;
import com.ochiamalu.practice.vo.UnCompletePracticeSetVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 练习套卷控制器
 *
 * @author OchiaMalu
 * @date 2024/08/29
 */
@RestController
@RequestMapping("/practice/set")
@Slf4j
public class PracticeSetController {

    @Resource
    private PracticeSetService practiceSetService;

    @RequestMapping("getSpecialPracticeContent")
    public Result<List<SpecialPracticeVO>> getSpecialPracticeContent() {
        List<SpecialPracticeVO> result = practiceSetService.getSpecialPracticeContent();
        if (log.isInfoEnabled()) {
            log.info("getSpecialPracticeContent.result:{}", JSON.toJSONString(result));
        }
        return Result.ok(result);
    }

    /**
     * 开始练习
     */
    @PostMapping(value = "/addPractice")
    public Result<PracticeSetVO> addPractice(@RequestBody GetPracticeSubjectListReq req) {
        PracticeSubjectDTO dto = new PracticeSubjectDTO();
        dto.setAssembleIds(req.getAssembleIds());
        PracticeSetVO practiceSetVO = practiceSetService.addPractice(dto);
        return Result.ok(practiceSetVO);
    }

    /**
     * 获取练习题
     */
    @PostMapping(value = "/getSubjects")
    public Result<PracticeSubjectListVO> getSubjects(@RequestBody GetPracticeSubjectsReq req) {
        PracticeSubjectListVO list = practiceSetService.getSubjects(req);
        return Result.ok(list);
    }

    /**
     * 获取题目详情
     */
    @PostMapping(value = "/getPracticeSubject")
    public Result<PracticeSubjectVO> getPracticeSubject(@RequestBody GetPracticeSubjectReq req) {
        PracticeSubjectDTO dto = new PracticeSubjectDTO();
        dto.setSubjectId(req.getSubjectId());
        dto.setSubjectType(req.getSubjectType());
        PracticeSubjectVO vo = practiceSetService.getPracticeSubject(dto);
        if (log.isInfoEnabled()) {
            log.info("获取练习题目详情出参{}", JSON.toJSONString(vo));
        }
        return Result.ok(vo);
    }

    /**
     * 获取模拟套题内容
     */
    @PostMapping(value = "/getPreSetContent")
    public Result<PageResult<PracticeSetVO>> getPreSetContent(@RequestBody GetPreSetReq req) {
        PracticeSetDTO dto = new PracticeSetDTO();
        dto.setOrderType(req.getOrderType());
        dto.setPageInfo(req.getPageInfo());
        dto.setSetName(req.getSetName());
        PageResult<PracticeSetVO> list = practiceSetService.getPreSetContent(dto);
        if (log.isInfoEnabled()) {
            log.info("获取模拟套题内容出参{}", JSON.toJSONString(list));
        }
        return Result.ok(list);
    }

    /**
     * 获取未完成的练题内容
     */
    @PostMapping(value = "/getUnCompletePractice")
    public Result<PageResult<UnCompletePracticeSetVO>> getUnCompletePractice(@RequestBody GetUnCompletePracticeReq req) {
        PageResult<UnCompletePracticeSetVO> list = practiceSetService.getUnCompletePractice(req);
        if (log.isInfoEnabled()) {
            log.info("获取未完成练习内容出参{}", JSON.toJSONString(list));
        }
        return Result.ok(list);
    }

}
