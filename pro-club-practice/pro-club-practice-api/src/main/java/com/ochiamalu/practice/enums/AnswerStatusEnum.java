package com.ochiamalu.practice.enums;

import lombok.Getter;

/**
 * 回答状态枚举
 *
 * @author OchiaMalu
 * @date 2024/08/29
 */
@Getter
public enum AnswerStatusEnum {

    /**
     * 错误
     */
    ERROR(0, "错误"),

    /**
     * 正确
     */
    CORRECT(1, "正确");

    final private int code;
    final private String desc;

    AnswerStatusEnum(int code, String desc) {
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