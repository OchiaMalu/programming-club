package com.ochiamalu.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 权限角色关联bo
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Data
public class AuthRolePermissionBO implements Serializable {
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

}