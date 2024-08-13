package com.ochiamalu.auth.application.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * 角色dto
 *
 * @author OchiaMalu
 * @date 2024/08/13
 */
@Data
public class AuthRoleDTO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色唯一标识
     */
    private String roleKey;

}