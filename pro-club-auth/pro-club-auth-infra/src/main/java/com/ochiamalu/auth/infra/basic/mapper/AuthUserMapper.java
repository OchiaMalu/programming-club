package com.ochiamalu.auth.infra.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ochiamalu.auth.infra.basic.entity.AuthUser;
import org.apache.ibatis.annotations.Mapper;

/**
* @author OchiaMalu
* @description 针对表【auth_user(用户信息表)】的数据库操作Mapper
* @createDate 2024-08-13 15:43:17
* @Entity .entity.AuthUser
*/
@Mapper
public interface AuthUserMapper extends BaseMapper<AuthUser> {

}




