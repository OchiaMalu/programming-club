package com.ochiamalu.auth.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochiamalu.auth.domain.entity.AuthRole;
import org.apache.ibatis.annotations.Mapper;

/**
* @author OchiaMalu
* @description 针对表【auth_role】的数据库操作Mapper
* @createDate 2024-08-13 19:22:20
* @Entity .entity.AuthRole
*/
@Mapper
public interface AuthRoleMapper extends BaseMapper<AuthRole> {

}




