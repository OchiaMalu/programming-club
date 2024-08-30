package com.ochiamalu.practice.server.service;


import com.ochiamalu.practice.common.PageResult;
import com.ochiamalu.practice.req.GetPracticeSubjectsReq;
import com.ochiamalu.practice.req.GetUnCompletePracticeReq;
import com.ochiamalu.practice.server.entity.dto.PracticeSetDTO;
import com.ochiamalu.practice.server.entity.dto.PracticeSubjectDTO;
import com.ochiamalu.practice.vo.PracticeSetVO;
import com.ochiamalu.practice.vo.PracticeSubjectListVO;
import com.ochiamalu.practice.vo.PracticeSubjectVO;
import com.ochiamalu.practice.vo.SpecialPracticeVO;
import com.ochiamalu.practice.vo.UnCompletePracticeSetVO;

import java.util.List;

/**
 * 练习套卷服务
 *
 * @author OchiaMalu
 * @date 2024/08/29
 */
public interface PracticeSetService {

    /**
     * 获取专项练习内容
     */
    List<SpecialPracticeVO> getSpecialPracticeContent();

    /**
     * 开始练习
     */
    PracticeSetVO addPractice(PracticeSubjectDTO dto);

    /**
     * 获取练习题
     */
    PracticeSubjectListVO getSubjects(GetPracticeSubjectsReq req);

    /**
     * 获取题目
     */
    PracticeSubjectVO getPracticeSubject(PracticeSubjectDTO dto);

    /**
     * 获取模拟套题内容
     */
    PageResult<PracticeSetVO> getPreSetContent(PracticeSetDTO dto);

    /**
     * 获取未完成练习内容
     */
    PageResult<UnCompletePracticeSetVO> getUnCompletePractice(GetUnCompletePracticeReq req);

}
