package com.ochiamalu.practice.server.utils;


import com.ochiamalu.practice.server.config.context.LoginContextHolder;


/**
 * 登录util
 *
 * @author OchiaMalu
 * @date 2024/08/29
 */
public class LoginUtil {

    public static String getLoginId() {
        return LoginContextHolder.getLoginId();
    }


}
