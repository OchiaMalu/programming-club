package com.ochiamalu.subject.domain.service.impl;

import com.ochiamalu.subject.common.entity.PageResult;
import com.ochiamalu.subject.common.utils.IdWorkerUtil;
import com.ochiamalu.subject.common.utils.LoginUtil;
import com.ochiamalu.subject.domain.convert.SubjectInfoConverter;
import com.ochiamalu.subject.domain.entity.SubjectInfoBO;
import com.ochiamalu.subject.domain.entity.SubjectOptionBO;
import com.ochiamalu.subject.domain.handler.subject.SubjectTypeHandler;
import com.ochiamalu.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.ochiamalu.subject.domain.service.SubjectInfoDomainService;
import com.ochiamalu.subject.infra.basic.entity.SubjectInfo;
import com.ochiamalu.subject.infra.basic.entity.SubjectInfoEs;
import com.ochiamalu.subject.infra.basic.entity.SubjectLabel;
import com.ochiamalu.subject.infra.basic.entity.SubjectMapping;
import com.ochiamalu.subject.infra.basic.service.SubjectEsService;
import com.ochiamalu.subject.infra.basic.service.SubjectInfoService;
import com.ochiamalu.subject.infra.basic.service.SubjectLabelService;
import com.ochiamalu.subject.infra.basic.service.SubjectMappingService;
import com.ochiamalu.subject.infra.entity.UserInfo;
import com.ochiamalu.subject.infra.rpc.UserRPC;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 题目服务实现
 *
 * @author OchiaMalu
 * @date 2024/08/09
 */
@Service
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectEsService subjectEsService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserRPC userRPC;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE
                .convertBO2Info(subjectInfoBO);
        subjectInfoService.save(subjectInfo);
        subjectInfoBO.setId(subjectInfo.getId());
        SubjectTypeHandler subjectTypeHandler = subjectTypeHandlerFactory
                .getSubjectTypeHandler(subjectInfo.getSubjectType());
        subjectTypeHandler.add(subjectInfoBO);

        ArrayList<SubjectMapping> subjectMappingList = getSubjectMappingList(subjectInfoBO);
        subjectMappingService.saveBatch(subjectMappingList);

        //同步到es
        SubjectInfoEs subjectInfoEs = getSubjectInfoEs(subjectInfoBO, subjectInfo);
        subjectEsService.addSubject(subjectInfoEs);

        //放入zset计入排行榜
        stringRedisTemplate.opsForZSet().incrementScore("subject:rank", LoginUtil.getLoginId(), 1);
    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBO2Info(subjectInfoBO);
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId()
                , subjectInfoBO.getLabelId());
        if (count == 0) {
            return pageResult;
        }
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId()
                , subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOList = SubjectInfoConverter.INSTANCE.convertInfo2BOList(subjectInfoList);
        subjectInfoBOList.forEach(info -> {
            List<SubjectMapping> mappingList = subjectMappingService.queryBySubjectId(info.getId());
            getLabelName(info, mappingList);
        });
        pageResult.setRecords(subjectInfoBOList);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.getById(subjectInfoBO.getId());
        SubjectTypeHandler handler = subjectTypeHandlerFactory
                .getSubjectTypeHandler(subjectInfo.getSubjectType());
        SubjectOptionBO optionBO = handler.query(subjectInfo.getId());
        SubjectInfoBO bo = SubjectInfoConverter.INSTANCE.convertOptionAndInfoToBo(optionBO, subjectInfo);
        List<SubjectMapping> mappingList = subjectMappingService.queryBySubjectId(subjectInfo.getId());
        getLabelName(bo, mappingList);
        return bo;
    }

    @Override
    public PageResult<SubjectInfoEs> searchSubjects(SubjectInfoBO subjectInfoBO) {
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setPageNo(subjectInfoBO.getPageNo());
        subjectInfoEs.setPageSize(subjectInfoBO.getPageSize());
        subjectInfoEs.setKeyWord(subjectInfoBO.getKeyword());
        return subjectEsService.searchSubjects(subjectInfoEs);
    }

    @Override
    public List<SubjectInfoBO> getContributeList() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringRedisTemplate
                .opsForZSet().rangeByScoreWithScores("subject:rank", 0, 5);
        if (CollectionUtils.isEmpty(typedTuples)) {
            return Collections.emptyList();
        }
        LinkedList<SubjectInfoBO> subjectInfoBOS = new LinkedList<>();
        typedTuples.forEach((rank) -> {
            SubjectInfoBO subjectInfoBO = new SubjectInfoBO();
            subjectInfoBO.setSubjectCount(Objects.requireNonNull(rank.getScore()).intValue());
            UserInfo userInfo = userRPC.getUserInfo(rank.getValue());
            subjectInfoBO.setCreateUser(userInfo.getNickName());
            subjectInfoBO.setCreateUserAvatar(userInfo.getAvatar());
            subjectInfoBOS.add(subjectInfoBO);
        });
        return subjectInfoBOS;
    }

    private void getLabelName(SubjectInfoBO bo, List<SubjectMapping> mappingList) {
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNameList = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        bo.setLabelName(labelNameList);
    }

    private static @NotNull ArrayList<SubjectMapping> getSubjectMappingList(SubjectInfoBO subjectInfoBO) {
        ArrayList<SubjectMapping> subjectMappingList = new ArrayList<>();
        List<Long> categoryIds = subjectInfoBO.getCategoryIds();
        List<Long> labelIds = subjectInfoBO.getLabelIds();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfoBO.getId());
                subjectMapping.setCategoryId(categoryId);
                subjectMapping.setLabelId(labelId);
                subjectMappingList.add(subjectMapping);
            });
        });
        return subjectMappingList;
    }

    private static @NotNull SubjectInfoEs getSubjectInfoEs(SubjectInfoBO subjectInfoBO, SubjectInfo subjectInfo) {
        SubjectInfoEs subjectInfoEs = new SubjectInfoEs();
        subjectInfoEs.setDocId(new IdWorkerUtil(1, 1, 1).nextId());
        subjectInfoEs.setSubjectId(subjectInfo.getId());
        subjectInfoEs.setSubjectType(subjectInfo.getSubjectType());
        subjectInfoEs.setSubjectName(subjectInfo.getSubjectName());
        subjectInfoEs.setSubjectAnswer(subjectInfoBO.getSubjectAnswer());
        subjectInfoEs.setCreateTime(new Date().getTime());
        subjectInfoEs.setCreateUser("ochiamalu");
        return subjectInfoEs;
    }
}
