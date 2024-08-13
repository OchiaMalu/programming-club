package com.ochiamalu.auth.application.controller;

import com.ochiamalu.auth.application.convert.AuthRoleDTOConverter;
import com.ochiamalu.auth.application.dto.AuthRoleDTO;
import com.ochiamalu.auth.common.entity.Result;
import com.ochiamalu.auth.domain.entity.AuthRoleBO;
import com.ochiamalu.auth.domain.service.AuthRoleDomainService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 角色控制器
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@RestController
@RequestMapping("/auth/role")
public class RoleController {

    @Resource
    private AuthRoleDomainService authRoleDomainService;

    @PostMapping("/add")
    public Result<Boolean> addRole(@RequestBody AuthRoleDTO authRoleDTO) {
        AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE
                .convertDTO2BO(authRoleDTO);
        Boolean result = authRoleDomainService.add(authRoleBO);
        return Result.ok(result);
    }

    @PutMapping("/update")
    public Result<Boolean> updateRole(@RequestBody AuthRoleDTO authRoleDTO) {
        AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE
                .convertDTO2BO(authRoleDTO);
        Boolean result = authRoleDomainService.update(authRoleBO);
        return Result.ok(result);
    }

    @DeleteMapping("/delete")
    public Result<Boolean> deleteRole(@RequestBody AuthRoleDTO authRoleDTO) {
        AuthRoleBO authRoleBO = AuthRoleDTOConverter.INSTANCE
                .convertDTO2BO(authRoleDTO);
        Boolean result = authRoleDomainService.delete(authRoleBO);
        return Result.ok(result);
    }
}
