package com.hh.baselibrary.util.check;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create By hHui on 2018/12/3
 */


public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    public static boolean isTrimEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isSpace(String s) {
        if (s == null) {
            return true;
        } else {
            int i = 0;

            for (int len = s.length(); i < len; ++i) {
                if (!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return ("A" + stringBuilder.toString()).toLowerCase();
    }

    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) {
            return true;
        } else {
            int length;
            if (a != null && b != null && (length = a.length()) == b.length()) {
                if (a instanceof String && b instanceof String) {
                    return a.equals(b);
                } else {
                    for (int i = 0; i < length; ++i) {
                        if (a.charAt(i) != b.charAt(i)) {
                            return false;
                        }
                    }

                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public static boolean equalsIgnoreCase(String a, String b) {
        return a == null ? b == null : a.equalsIgnoreCase(b);
    }

    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    public static String upperFirstLetter(String s) {
        return !isEmpty(s) && Character.isLowerCase(s.charAt(0)) ? (char) (s.charAt(0) - 32) + s.substring(1) : s;
    }

    public static String lowerFirstLetter(String s) {
        return !isEmpty(s) && Character.isUpperCase(s.charAt(0)) ? (char) (s.charAt(0) + 32) + s.substring(1) : s;
    }

    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) {
            return s;
        } else {
            int mid = len >> 1;
            char[] chars = s.toCharArray();

            for (int i = 0; i < mid; ++i) {
                char c = chars[i];
                chars[i] = chars[len - i - 1];
                chars[len - i - 1] = c;
            }

            return new String(chars);
        }
    }

    public static String toDBC(String s) {
        if (isEmpty(s)) {
            return s;
        } else {
            char[] chars = s.toCharArray();
            int i = 0;

            for (int len = chars.length; i < len; ++i) {
                if (chars[i] == 12288) {
                    chars[i] = 32;
                } else if ('！' <= chars[i] && chars[i] <= '～') {
                    chars[i] -= 'ﻠ';
                } else {
                    chars[i] = chars[i];
                }
            }

            return new String(chars);
        }
    }

    public static String toSBC(String s) {
        if (isEmpty(s)) {
            return s;
        } else {
            char[] chars = s.toCharArray();
            int i = 0;

            for (int len = chars.length; i < len; ++i) {
                if (chars[i] == 32) {
                    chars[i] = 12288;
                } else if (33 <= chars[i] && chars[i] <= 126) {
                    chars[i] += 'ﻠ';
                } else {
                    chars[i] = chars[i];
                }
            }

            return new String(chars);
        }
    }

    // ipv4正则校验
    public static boolean isIpv4(String ipv4) {
        if (ipv4 == null || ipv4.length() == 0) {
            return false;//字符串为空或者空串
        }
        String[] parts = ipv4.split("\\.");
        if (parts.length != 4) {
            return false;//分割开的数组根本就不是4个数字
        }
        for (int i = 0; i < parts.length; i++) {
            try {
                int n = Integer.parseInt(parts[i]);
                if (n < 0 || n > 255) {
                    return false;//数字不在正确范围内
                }
            } catch (NumberFormatException e) {
                return false;//转换数字不正确
            }
        }
        return true;
    }


}
