package com.ochiamalu.subject.domain.service.impl;

import com.ochiamalu.subject.domain.convert.SubjectCategoryConverter;
import com.ochiamalu.subject.domain.convert.SubjectLabelConverter;
import com.ochiamalu.subject.domain.entity.SubjectCategoryBO;
import com.ochiamalu.subject.domain.entity.SubjectLabelBO;
import com.ochiamalu.subject.domain.service.SubjectCategoryDomainService;
import com.ochiamalu.subject.infra.basic.entity.SubjectCategory;
import com.ochiamalu.subject.infra.basic.entity.SubjectLabel;
import com.ochiamalu.subject.infra.basic.entity.SubjectMapping;
import com.ochiamalu.subject.infra.basic.service.SubjectCategoryService;
import com.ochiamalu.subject.infra.basic.service.SubjectLabelService;
import com.ochiamalu.subject.infra.basic.service.SubjectMappingService;
import lombok.SneakyThrows;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private ThreadPoolTaskExecutor labelThreadPool;

    @Override
    public boolean add(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBO2Category(subjectCategoryBO);
        return subjectCategoryService.save(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryPrimaryCategory() {
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryPrimaryCategory();
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE
                .convertCategoryList2BO(subjectCategoryList);
        subjectCategoryBOList.forEach((subjectCategoryBO) -> {
            Integer count = subjectCategoryService.countSubject(subjectCategoryBO.getId());
            subjectCategoryBO.setCount(count);
        });
        return subjectCategoryBOList;
    }

    @Override
    public List<SubjectCategoryBO> queryCategoryByPrimary(Long id) {
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategoryByPrimary(id);
        return SubjectCategoryConverter.INSTANCE
                .convertCategoryList2BO(subjectCategoryList);
    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBO2Category(subjectCategoryBO);
        return subjectCategoryService.updateById(subjectCategory);
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBO2Category(subjectCategoryBO);
        return subjectCategoryService.removeById(subjectCategory);
    }

    @SneakyThrows
    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBO2Category(subjectCategoryBO);
        List<SubjectCategory> subjectCategoryList = subjectCategoryService
                .listById(subjectCategory.getId());
        List<SubjectCategoryBO> categoryBOList = SubjectCategoryConverter.INSTANCE
                .convertCategoryList2BO(subjectCategoryList);

        Map<Long, List<SubjectLabelBO>> map = new HashMap<>();
        List<CompletableFuture<Map<Long, List<SubjectLabelBO>>>> completableFutureList = categoryBOList.stream().map(category ->
                CompletableFuture.supplyAsync(() -> getLabelBOList(category.getId()), labelThreadPool)
        ).collect(Collectors.toList());
        completableFutureList.forEach(future -> {
            try {
                Map<Long, List<SubjectLabelBO>> resultMap = future.get();
                map.putAll(resultMap);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        categoryBOList.forEach((categoryBO) -> {
            categoryBO.setLabelList(map.get(categoryBO.getId()));
        });
        return categoryBOList;

//        List<FutureTask<Map<Long, List<SubjectLabelBO>>>> futureTaskList = new LinkedList<>();
//        Map<Long, List<SubjectLabelBO>> map = new HashMap<>();
//        categoryBOList.forEach((categoryBO) -> {
//            FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask = new FutureTask<>(() ->
//                    getLabelBOList(categoryBO.getId())
//            );
//            futureTaskList.add(futureTask);
//            labelThreadPool.submit((Runnable) futureTaskList);
//        });
//        for (FutureTask<Map<Long, List<SubjectLabelBO>>> futureTask : futureTaskList) {
//            Map<Long, List<SubjectLabelBO>> resultMap = futureTask.get();
//            if (CollectionUtils.isEmpty(resultMap)) {
//                continue;
//            }
//            map.putAll(resultMap);
//        }
//        categoryBOList.forEach((categoryBO) -> {
//            categoryBO.setLabelList(map.get(categoryBO.getId()));
//        });
//        return categoryBOList;
    }

    private Map<Long, List<SubjectLabelBO>> getLabelBOList(Long categoryId) {
        Map<Long, List<SubjectLabelBO>> labelMap = new HashMap<>();
        List<SubjectMapping> subjectMappingList = subjectMappingService
                .queryByCategoryId(categoryId);
        Set<Long> labelIdList = subjectMappingList.stream()
                .map(SubjectMapping::getLabelId).collect(Collectors.toSet());
        if (labelIdList.isEmpty()) {
            return null;
        }
        List<SubjectLabel> subjectLabelList = subjectLabelService.listByIds(labelIdList);
        List<SubjectLabelBO> subjectLabelBOList = SubjectLabelConverter.INSTANCE
                .convertLabel2BOList(subjectLabelList);
        labelMap.put(categoryId, subjectLabelBOList);
        return labelMap;
    }
}
