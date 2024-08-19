package com.ochiamalu.subject.common.utils;

import com.ochiamalu.subject.common.context.LoginContextHolder;

/**
 * 登录util
 *
 * @author OchiaMalu
 * @date 2024/08/16
 */
public class LoginUtil {
    public static String getLoginId() {
        return LoginContextHolder.getLoginId();
    }
}
