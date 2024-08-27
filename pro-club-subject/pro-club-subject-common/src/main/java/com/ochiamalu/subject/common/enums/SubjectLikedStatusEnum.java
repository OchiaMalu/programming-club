package com.ochiamalu.subject.common.enums;

import lombok.Getter;

@Getter
public enum SubjectLikedStatusEnum {

    LIKED(1, "点赞"),

    UN_LIKED(0, "未点赞");

    private final int code;

    private final String desc;

    SubjectLikedStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SubjectLikedStatusEnum getResultCodeEnum(int code) {
        for (SubjectLikedStatusEnum resultCodeEnum : SubjectLikedStatusEnum.values()) {
            if (resultCodeEnum.getCode() == code) {
                return resultCodeEnum;
            }
        }
        return null;
    }
}
