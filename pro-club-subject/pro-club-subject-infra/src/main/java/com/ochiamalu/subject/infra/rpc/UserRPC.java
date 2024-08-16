package com.ochiamalu.subject.infra.rpc;

import com.ochiamalu.auth.api.UserFeignService;
import com.ochiamalu.auth.entity.AuthUserDTO;
import com.ochiamalu.auth.entity.Result;
import com.ochiamalu.subject.infra.entity.UserInfo;

import javax.annotation.Resource;

/**
 * 用户rpc
 *
 * @author OchiaMalu
 * @date 2024/08/16
 */
public class UserRPC {

    @Resource
    private UserFeignService userFeignService;

    public UserInfo getUserInfo(String userName) {
        AuthUserDTO authUserDTO = new AuthUserDTO();
        authUserDTO.setUserName(userName);
        Result<AuthUserDTO> result = userFeignService.getUserInfo(authUserDTO);
        UserInfo userInfo = new UserInfo();
        if (!result.getSuccess()) {
            return userInfo;
        }
        AuthUserDTO data = result.getData();
        userInfo.setUserName(data.getUserName());
        userInfo.setNickName(data.getNickName());
        return userInfo;
    }
}
