package com.ochiamalu.practice.enums;

import lombok.Getter;

/**
 * 套卷类型枚举
 *
 * @author OchiaMalu
 * @date 2024/08/29
 */
@Getter
public enum SetTypeEnum {

    /**
     * 实时生成
     */
    REAL(1, "实时生成"),

    /**
     * 预设套题
     */
    PRESET(2, "预设套题");

    final private int code;
    final private String desc;

    SetTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}