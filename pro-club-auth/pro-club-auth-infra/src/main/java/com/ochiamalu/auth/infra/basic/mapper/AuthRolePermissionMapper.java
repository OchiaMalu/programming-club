package com.ochiamalu.auth.infra.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochiamalu.auth.infra.basic.entity.AuthRolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
* @author OchiaMalu
* @description 针对表【auth_role_permission(角色权限关联表)】的数据库操作Mapper
* @createDate 2024-08-13 19:22:32
* @Entity .entity.AuthRolePermission
*/
@Mapper
public interface AuthRolePermissionMapper extends BaseMapper<AuthRolePermission> {

}




