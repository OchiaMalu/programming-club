package com.ochiamalu.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


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

    /**
     * 权限id列表
     */
    private List<Long> permissionIdList;
}