package com.ochiamalu.auth.api;

import com.ochiamalu.auth.entity.AuthUserDTO;
import com.ochiamalu.auth.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户服务feign
 *
 * @author OchiaMalu
 * @date 2024/08/16
 */
@FeignClient("pro-club-auth-dev")
public interface UserFeignService {

    @GetMapping("/auth/user/getUserInfo")
    Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO);
}
