package com.ochiamalu.subject.infra.basic.utils;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 德鲁伊加密工具
 *
 * @author OchiaMalu
 * @date 2024/08/06
 */
public class DruidEncryptUtil {
    private static String publicKey;
    private static String privateKey;

    static {
        try {
            String[] keyPair = ConfigTools.genKeyPair(512);
            privateKey = keyPair[0];
            System.out.println("privateKey = " + privateKey);
            publicKey = keyPair[1];
            System.out.println("publicKey = " + publicKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String plainText) throws Exception {
        String encrypted = ConfigTools.encrypt(privateKey, plainText);
        System.out.println("encrypted = " + encrypted);
        return encrypted;
    }

    public static String decrypt(String encryptedText) throws Exception {
        String decrypt = ConfigTools.decrypt(publicKey, encryptedText);
        System.out.println("decrypt = " + decrypt);
        return decrypt;
    }

    public static void main(String[] args) throws Exception{
        encrypt("123456");
        encrypt("root");
    }
}
