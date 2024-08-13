package com.ochiamalu.auth.application.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * 用户角色dto
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Data
public class AuthUserRoleDTO implements Serializable {
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