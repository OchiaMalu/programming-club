package com.ochiamalu.practice.server.controller;

import com.alibaba.fastjson.JSON;
import com.ochiamalu.practice.common.Result;
import com.ochiamalu.practice.req.GetReportReq;
import com.ochiamalu.practice.req.GetScoreDetailReq;
import com.ochiamalu.practice.req.GetSubjectDetailReq;
import com.ochiamalu.practice.req.SubmitPracticeDetailReq;
import com.ochiamalu.practice.req.SubmitSubjectDetailReq;
import com.ochiamalu.practice.server.service.PracticeDetailService;
import com.ochiamalu.practice.vo.RankVO;
import com.ochiamalu.practice.vo.ReportVO;
import com.ochiamalu.practice.vo.ScoreDetailVO;
import com.ochiamalu.practice.vo.SubjectDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 练习细节控制器
 *
 * @author OchiaMalu
 * @date 2024/08/29
 */
@RestController
@Slf4j
@RequestMapping("/practice/detail")
public class PracticeDetailController {

    @Resource
    private PracticeDetailService practiceDetailService;

    /**
     * 提交题目
     */
    @PostMapping(value = "/submitSubject")
    public Result<Boolean> submitSubject(@RequestBody SubmitSubjectDetailReq req) {
        Boolean result = practiceDetailService.submitSubject(req);
        log.info("练习提交题目出参{}", result);
        return Result.ok(result);
    }

    /**
     * 提交练题情况
     */
    @PostMapping(value = "/submit")
    public Result<Boolean> submit(@RequestBody SubmitPracticeDetailReq req) {
        Boolean result = practiceDetailService.submit(req);
        if (log.isInfoEnabled()) {
            log.info("提交练题情况出参{}", JSON.toJSONString(result));
        }
        return Result.ok(result);
    }

    /**
     * 答案解析-每题得分
     */
    @PostMapping(value = "/getScoreDetail")
    public Result<List<ScoreDetailVO>> getScoreDetail(@RequestBody GetScoreDetailReq req) {
        List<ScoreDetailVO> list = practiceDetailService.getScoreDetail(req);
        if (log.isInfoEnabled()) {
            log.info("每题得分出参{}", JSON.toJSONString(list));
        }
        return Result.ok(list);
    }

    /**
     * 答案解析-答题详情
     */
    @PostMapping(value = "/getSubjectDetail")
    public Result<SubjectDetailVO> getSubjectDetail(@RequestBody GetSubjectDetailReq req) {
        SubjectDetailVO subjectDetailVO = practiceDetailService.getSubjectDetail(req);
        if (log.isInfoEnabled()) {
            log.info("答案详情出参{}", JSON.toJSONString(subjectDetailVO));
        }
        return Result.ok(subjectDetailVO);
    }

    /**
     * 答案解析-评估报告
     */
    @PostMapping(value = "/getReport")
    public Result<ReportVO> getReport(@RequestBody GetReportReq req) {
        ReportVO reportVO = practiceDetailService.getReport(req);
        if (log.isInfoEnabled()) {
            log.info("获取评估报告出参{}", JSON.toJSONString(reportVO));
        }
        return Result.ok(reportVO);
    }

    /**
     * 获取练习榜
     */
    @PostMapping(value = "/getPracticeRankList")
    public Result<List<RankVO>> getPracticeRankList() {
        List<RankVO> list = practiceDetailService.getPracticeRankList();
        if (log.isInfoEnabled()) {
            log.info("练习榜出参{}", list);
        }
        return Result.ok(list);
    }

    /**
     * 放弃练习
     */
    @PostMapping(value = "/giveUp")
    public Result<Boolean> giveUp(@RequestParam("practiceId") Long practiceId) {
        Boolean result = practiceDetailService.giveUp(practiceId);
        log.info("放弃练习出参{}", result);
        return Result.ok(result);
    }

}