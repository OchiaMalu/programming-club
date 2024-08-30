package com.ochiamalu.auth.infra.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochiamalu.auth.infra.basic.entity.AuthPermission;
import org.apache.ibatis.annotations.Mapper;

/**
* @author OchiaMalu
* @description 针对表【auth_permission】的数据库操作Mapper
* @createDate 2024-08-13 19:22:27
* @Entity .common.AuthPermission
*/
@Mapper
public interface AuthPermissionMapper extends BaseMapper<AuthPermission> {

}




