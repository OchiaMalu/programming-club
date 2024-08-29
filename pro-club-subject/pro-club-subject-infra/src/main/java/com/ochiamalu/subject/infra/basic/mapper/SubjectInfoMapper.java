package com.ochiamalu.subject.infra.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochiamalu.subject.infra.basic.entity.SubjectInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author OchiaMalu
* @description 针对表【subject_info(题目信息表)】的数据库操作Mapper
* @createDate 2024-08-09 18:30:00
* @Entity com.ochiamalu.subject.infra.basic.entity.SubjectInfo
*/
public interface SubjectInfoMapper extends BaseMapper<SubjectInfo> {

    int countByCondition(@Param("subjectInfo") SubjectInfo subjectInfo,
                         @Param("categoryId") Long categoryId,
                         @Param("labelId") Long labelId);

    List<SubjectInfo> queryPage(@Param("subjectInfo") SubjectInfo subjectInfo,
                                @Param("categoryId") Long categoryId,
                                @Param("labelId") Long labelId,
                                @Param("start") int start,
                                @Param("pageSize") Integer pageSize);

    Long querySubjectIdCursor(@Param("subjectId") Long subjectId,
                              @Param("categoryId") Long categoryId,
                              @Param("labelId") Long labelId,
                              @Param("cursor") int cursor);
}




