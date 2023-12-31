package com.hh.baselibrary.util.encrypt;

import java.security.MessageDigest;

/**
 * MD5加密工具
 */
public class Md5Util {

    /**
     * MD5加密
     *
     * @param dataStr
     * @return
     */
    public static String encrypt(String dataStr) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
