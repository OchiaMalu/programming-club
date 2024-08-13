package com.ochiamalu.auth.application.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * 权限角色关联dto
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Data
public class AuthRolePermissionDTO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 权限id列表
     */
    private List<Long> permissionIdList;

}