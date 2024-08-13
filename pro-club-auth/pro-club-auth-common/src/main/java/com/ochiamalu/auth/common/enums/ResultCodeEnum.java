package com.ochiamalu.auth.common.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "成功"),

    FAIL(500, "失败");

    private final int code;

    private final String desc;

    ResultCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResultCodeEnum getResultCodeEnum(int code) {
        for (ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()) {
            if (resultCodeEnum.getCode() == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
