package com.ochiamalu.auth.application.controller;

import com.ochiamalu.auth.application.convert.AuthRolePermissionDTOConverter;
import com.ochiamalu.auth.application.dto.AuthRolePermissionDTO;
import com.ochiamalu.auth.domain.entity.AuthRolePermissionBO;
import com.ochiamalu.auth.domain.service.AuthRolePermissionDomainService;
import com.ochiamalu.auth.entity.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色权限控制器
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@RestController
@RequestMapping("/auth/rolePermission")
public class AuthRolePermissionController {

    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody AuthRolePermissionDTO authRolePermissionDTO) {
        AuthRolePermissionBO authRolePermissionBO = AuthRolePermissionDTOConverter.INSTANCE
                .convertDTO2BO(authRolePermissionDTO);
        Boolean result = authRolePermissionDomainService.add(authRolePermissionBO);
        return Result.ok(result);
    }
}
