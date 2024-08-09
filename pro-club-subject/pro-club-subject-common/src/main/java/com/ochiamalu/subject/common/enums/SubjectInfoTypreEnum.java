package com.ochiamalu.subject.common.enums;

import lombok.Getter;

@Getter
public enum SubjectInfoTypreEnum {

    RADIO(1, "单选"),

    MULTIPLE(2, "多选"),

    JUDGE(3, "判断"),

    BRIEF(4, "简答");

    private final int code;

    private final String desc;

    SubjectInfoTypreEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SubjectInfoTypreEnum getResultCodeEnum(int code) {
        for (SubjectInfoTypreEnum resultCodeEnum : SubjectInfoTypreEnum.values()) {
            if (resultCodeEnum.getCode() == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
