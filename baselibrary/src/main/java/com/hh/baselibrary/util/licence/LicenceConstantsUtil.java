package com.hh.baselibrary.util.licence;

/**
 * Create By hHui on 2022/6/22 0022 上午 10:33
 */
public class LicenceConstantsUtil {
    static LicenceConstantsUtil instance;

    public static LicenceConstantsUtil getInstance() {
        if (instance == null)
            instance = new LicenceConstantsUtil();
        return instance;
    }


    static {
        System.loadLibrary("Licence");
    }

    public native String getSecretKey();

    public native String getIV();

    public native String getLicenceUrl();
}
