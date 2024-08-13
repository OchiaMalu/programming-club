package com.ochiamalu.auth.application.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ochiamalu.auth.application.convert.AuthUserDTOConverter;
import com.ochiamalu.auth.application.dto.AuthUserDTO;
import com.ochiamalu.auth.common.entity.Result;
import com.ochiamalu.auth.domain.entity.AuthUserBO;
import com.ochiamalu.auth.domain.service.AuthUserDomainService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth/user/")
public class UserController {

    @Resource
    private AuthUserDomainService authUserDomainService;

    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin?username=zhang&password=123456
    @RequestMapping("doLogin")
    public String doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("zhang".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001);
            return "登录成功";
        }
        return "登录失败";
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
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
}
