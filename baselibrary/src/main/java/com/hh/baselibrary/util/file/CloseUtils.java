package com.hh.baselibrary.util.file;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.Closeable;
import java.io.IOException;

public final class CloseUtils {
    private CloseUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void closeIO(Closeable... closeables) {
        if (closeables != null) {
            Closeable[] var1 = closeables;
            int var2 = closeables.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                Closeable closeable = var1[var3];
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException var6) {
                        var6.printStackTrace();
                    }
                }
            }

        }
    }

    public static void closeIOQuietly(Closeable... closeables) {
        if (closeables != null) {
            Closeable[] var1 = closeables;
            int var2 = closeables.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                Closeable closeable = var1[var3];
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException var6) {
                        ;
                    }
                }
            }

        }
    }
}

