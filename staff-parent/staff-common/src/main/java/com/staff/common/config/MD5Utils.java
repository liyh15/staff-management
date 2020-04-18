package com.staff.common.config;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
    /**
     * MD5加密算法
     */
    public static String MD5(String password) {
        // 给初始密码添加密钥，并进行初始加密
        String md5Str = DigestUtils.md5Hex(password + "staffPassword!@#$%@!");
        for (int i = 0; i < 10 ; i++) {
            // 进行十次轮询加密
            md5Str = DigestUtils.md5Hex(md5Str + "staffPassword!@#$%@!" + i);
        }
        return md5Str;
    }
}
