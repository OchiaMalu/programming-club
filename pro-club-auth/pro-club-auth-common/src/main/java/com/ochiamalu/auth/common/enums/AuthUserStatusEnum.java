package com.ochiamalu.auth.common.enums;

import lombok.Getter;

@Getter
public enum AuthUserStatusEnum {
    ON(0, "启用"),

    CLOSE(1, "禁用");

    private final int code;

    private final String desc;

    AuthUserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AuthUserStatusEnum getAuthUserStatusEnum(int code) {
        for (AuthUserStatusEnum authUserStatusEnum : AuthUserStatusEnum.values()) {
            if (authUserStatusEnum.getCode() == code) {
                return authUserStatusEnum;
            }
        }
        return null;
    }
}
