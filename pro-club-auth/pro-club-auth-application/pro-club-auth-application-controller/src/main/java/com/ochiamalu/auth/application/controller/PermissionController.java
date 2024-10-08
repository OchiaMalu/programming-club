package com.ochiamalu.auth.application.controller;

import com.ochiamalu.auth.application.convert.AuthPermissionDTOConverter;
import com.ochiamalu.auth.application.dto.AuthPermissionDTO;
import com.ochiamalu.auth.domain.entity.AuthPermissionBO;
import com.ochiamalu.auth.domain.service.AuthPermissionDomainService;
import com.ochiamalu.auth.entity.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限控制器
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@RestController
@RequestMapping("/auth/permission")
public class PermissionController {

    @Resource
    private AuthPermissionDomainService authPermissionDomainService;

    @PostMapping("/add")
    public Result<Boolean> addPermission(@RequestBody AuthPermissionDTO authPermissionDTO) {
        AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE
                .convertDTO2BO(authPermissionDTO);
        Boolean result = authPermissionDomainService.add(authPermissionBO);
        return Result.ok(result);
    }

    @PutMapping("/update")
    public Result<Boolean> updatePermission(@RequestBody AuthPermissionDTO authPermissionDTO) {
        AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE
                .convertDTO2BO(authPermissionDTO);
        Boolean result = authPermissionDomainService.update(authPermissionBO);
        return Result.ok(result);
    }

    @DeleteMapping("/delete")
    public Result<Boolean> deletePermission(@RequestBody AuthPermissionDTO authPermissionDTO) {
        AuthPermissionBO authPermissionBO = AuthPermissionDTOConverter.INSTANCE
                .convertDTO2BO(authPermissionDTO);
        Boolean result = authPermissionDomainService.delete(authPermissionBO);
        return Result.ok(result);
    }

    @GetMapping("/getPermission")
    public Result<List<String>> getPermission(String userName) {
        List<String> permissionList = authPermissionDomainService.getPermission(userName);
        return Result.ok(permissionList);
    }
}
