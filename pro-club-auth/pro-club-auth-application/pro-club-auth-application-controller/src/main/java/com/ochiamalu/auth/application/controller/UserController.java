package com.ochiamalu.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.ochiamalu.auth.application.convert.AuthUserDTOConverter;
import com.ochiamalu.auth.domain.entity.AuthUserBO;
import com.ochiamalu.auth.domain.service.AuthUserDomainService;
import com.ochiamalu.auth.entity.AuthUserDTO;
import com.ochiamalu.auth.entity.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth/user")
public class UserController {

    @Resource
    private AuthUserDomainService authUserDomainService;

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @PostMapping("/login")
    public Result<SaTokenInfo> doLogin(String validCode) {
        Boolean result = authUserDomainService.doLogin(validCode);
        if (result) {
            return Result.ok(StpUtil.getTokenInfo());
        }
        return Result.fail();
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @GetMapping("/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    @PostMapping("/logout")
    public Result<Boolean> logout() {
        StpUtil.logout();
        return Result.ok(true);
    }

    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTO2BO(authUserDTO);
        Boolean result = authUserDomainService.register(authUserBO);
        return Result.ok(result);
    }

    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTO2BO(authUserDTO);
        Boolean result = authUserDomainService.update(authUserBO);
        return Result.ok(result);
    }

    @PutMapping("/status")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
        AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTO2BO(authUserDTO);
        Boolean result = authUserDomainService.changeStatus(authUserBO);
        return Result.ok(result);
    }

    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTO2BO(authUserDTO);
        Boolean result = authUserDomainService.delete(authUserBO);
        return Result.ok(result);
    }

    @GetMapping("/getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDTO2BO(authUserDTO);
        AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
        AuthUserDTO userInfoDTO = AuthUserDTOConverter.INSTANCE
                .convertBO2DTO(userInfo);
        return Result.ok(userInfoDTO);
    }
}
