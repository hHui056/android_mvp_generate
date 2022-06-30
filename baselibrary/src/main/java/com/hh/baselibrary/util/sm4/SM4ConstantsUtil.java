package com.hh.baselibrary.util.sm4;

/**
 * Create By hHui on 2022/6/20 0020 下午 16:00
 */
public class SM4ConstantsUtil {
    static SM4ConstantsUtil instance;

    public static SM4ConstantsUtil getInstance() {
        if (instance == null)
            instance = new SM4ConstantsUtil();
        return instance;
    }
    
    static {
        System.loadLibrary("sm4");
    }

    public native String getSM4SecretKey();

    public native String getSM4IV();
}
