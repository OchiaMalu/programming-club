package com.ochiamalu.auth.domain.entity;

import lombok.Data;

import java.io.Serializable;


/**
 * 用户角色bo
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Data
public class AuthUserRoleBO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;
}