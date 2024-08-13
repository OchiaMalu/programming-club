package com.ochiamalu.auth.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochiamalu.auth.domain.entity.AuthUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
* @author OchiaMalu
* @description 针对表【auth_user_role(用户角色表)】的数据库操作Mapper
* @createDate 2024-08-13 19:22:42
* @Entity .entity.AuthUserRole
*/
@Mapper
public interface AuthUserRoleMapper extends BaseMapper<AuthUserRole> {

}




