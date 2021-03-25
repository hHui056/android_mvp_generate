//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hh.baselibrary.util.file;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;

public final class FileIOUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");

    private FileIOUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean writeFileFromIS(String filePath, InputStream is) {
        return writeFileFromIS(FileUtils.getFileByPath(filePath), is, false);
    }

    public static boolean writeFileFromIS(String filePath, InputStream is, boolean append) {
        return writeFileFromIS(FileUtils.getFileByPath(filePath), is, append);
    }

    public static boolean writeFileFromIS(File file, InputStream is) {
        return writeFileFromIS(file, is, false);
    }

    public static boolean writeFileFromIS(File file, InputStream is, boolean append) {
        if (FileUtils.createOrExistsFile(file) && is != null) {
            BufferedOutputStream os = null;

            boolean var5;
            try {
                os = new BufferedOutputStream(new FileOutputStream(file, append));
                byte[] data = new byte[1024];

                int len;
                while ((len = is.read(data, 0, 1024)) != -1) {
                    os.write(data, 0, len);
                }
                return true;
            } catch (IOException var10) {
                var10.printStackTrace();
                var5 = false;
            } finally {
                CloseUtils.closeIO(new Closeable[]{is, os});
            }
            return var5;
        } else {
            return false;
        }
    }

    public static boolean writeFileFromBytesByStream(String filePath, byte[] bytes) {
        return writeFileFromBytesByStream(FileUtils.getFileByPath(filePath), bytes, false);
    }

    public static boolean writeFileFromBytesByStream(String filePath, byte[] bytes, boolean append) {
        return writeFileFromBytesByStream(FileUtils.getFileByPath(filePath), bytes, append);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bytes) {
        return writeFileFromBytesByStream(file, bytes, false);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bytes, boolean append) {
        if (bytes != null && FileUtils.createOrExistsFile(file)) {
            BufferedOutputStream bos = null;

            boolean var5;
            try {
                bos = new BufferedOutputStream(new FileOutputStream(file, append));
                bos.write(bytes);
                boolean var4 = true;
                return var4;
            } catch (IOException var9) {
                var9.printStackTrace();
                var5 = false;
            } finally {
                CloseUtils.closeIO(new Closeable[]{bos});
            }

            return var5;
        } else {
            return false;
        }
    }

    public static boolean writeFileFromBytesByChannel(String filePath, byte[] bytes, boolean isForce) {
        return writeFileFromBytesByChannel(FileUtils.getFileByPath(filePath), bytes, false, isForce);
    }

    public static boolean writeFileFromBytesByChannel(String filePath, byte[] bytes, boolean append, boolean isForce) {
        return writeFileFromBytesByChannel(FileUtils.getFileByPath(filePath), bytes, append, isForce);
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bytes, boolean isForce) {
        return writeFileFromBytesByChannel(file, bytes, false, isForce);
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bytes, boolean append, boolean isForce) {
        if (bytes == null) {
            return false;
        } else {
            FileChannel fc = null;

            boolean var6;
            try {
                fc = (new FileOutputStream(file, append)).getChannel();
                fc.position(fc.size());
                fc.write(ByteBuffer.wrap(bytes));
                if (isForce) {
                    fc.force(true);
                }

                boolean var5 = true;
                return var5;
            } catch (IOException var10) {
                var10.printStackTrace();
                var6 = false;
            } finally {
                CloseUtils.closeIO(new Closeable[]{fc});
            }

            return var6;
        }
    }

    public static boolean writeFileFromBytesByMap(String filePath, byte[] bytes, boolean isForce) {
        return writeFileFromBytesByMap(filePath, bytes, false, isForce);
    }

    public static boolean writeFileFromBytesByMap(String filePath, byte[] bytes, boolean append, boolean isForce) {
        return writeFileFromBytesByMap(FileUtils.getFileByPath(filePath), bytes, append, isForce);
    }

    public static boolean writeFileFromBytesByMap(File file, byte[] bytes, boolean isForce) {
        return writeFileFromBytesByMap(file, bytes, false, isForce);
    }

    public static boolean writeFileFromBytesByMap(File file, byte[] bytes, boolean append, boolean isForce) {
        if (bytes != null && FileUtils.createOrExistsFile(file)) {
            FileChannel fc = null;

            boolean var6;
            try {
                fc = (new FileOutputStream(file, append)).getChannel();
                MappedByteBuffer mbb = fc.map(MapMode.READ_WRITE, fc.size(), (long) bytes.length);
                mbb.put(bytes);
                if (isForce) {
                    mbb.force();
                }

                var6 = true;
                return var6;
            } catch (IOException var10) {
                var10.printStackTrace();
                var6 = false;
            } finally {
                CloseUtils.closeIO(new Closeable[]{fc});
            }

            return var6;
        } else {
            return false;
        }
    }

    public static boolean writeFileFromString(String filePath, String content) {
        return writeFileFromString(FileUtils.getFileByPath(filePath), content, false);
    }

    public static boolean writeFileFromString(String filePath, String content, boolean append) {
        return writeFileFromString(FileUtils.getFileByPath(filePath), content, append);
    }

    public static boolean writeFileFromString(File file, String content) {
        return writeFileFromString(file, content, false);
    }

    public static boolean writeFileFromString(File file, String content, boolean append) {
        if (file != null && content != null) {
            if (!FileUtils.createOrExistsFile(file)) {
                return false;
            } else {
                BufferedWriter bw = null;

                boolean var5;
                try {
                    bw = new BufferedWriter(new FileWriter(file, append));
                    bw.write(content);
                    boolean var4 = true;
                    return var4;
                } catch (IOException var9) {
                    var9.printStackTrace();
                    var5 = false;
                } finally {
                    CloseUtils.closeIO(new Closeable[]{bw});
                }

                return var5;
            }
        } else {
            return false;
        }
    }

    public static List<String> readFile2List(String filePath) {
        return readFile2List((File) FileUtils.getFileByPath(filePath), (String) null);
    }

    public static List<String> readFile2List(String filePath, String charsetName) {
        return readFile2List(FileUtils.getFileByPath(filePath), charsetName);
    }

    public static List<String> readFile2List(File file) {
        return readFile2List((File) file, 0, 2147483647, (String) null);
    }

    public static List<String> readFile2List(File file, String charsetName) {
        return readFile2List((File) file, 0, 2147483647, charsetName);
    }

    public static List<String> readFile2List(String filePath, int st, int end) {
        return readFile2List((File) FileUtils.getFileByPath(filePath), st, end, (String) null);
    }

    public static List<String> readFile2List(String filePath, int st, int end, String charsetName) {
        return readFile2List(FileUtils.getFileByPath(filePath), st, end, charsetName);
    }

    public static List<String> readFile2List(File file, int st, int end) {
        return readFile2List((File) file, st, end, (String) null);
    }

    public static List<String> readFile2List(File file, int st, int end, String charsetName) {
        if (!FileUtils.isFileExists(file)) {
            return null;
        } else if (st > end) {
            return null;
        } else {
            BufferedReader reader = null;

            Object var6;
            try {
                int curLine = 1;
                List<String> list = new ArrayList();
                if (isSpace(charsetName)) {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                } else {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
                }

                String line;
                for (; (line = reader.readLine()) != null && curLine <= end; ++curLine) {
                    if (st <= curLine && curLine <= end) {
                        list.add(line);
                    }
                }
                return list;
            } catch (IOException var12) {
                var12.printStackTrace();
                var6 = null;
            } finally {
                CloseUtils.closeIO(new Closeable[]{reader});
            }

            return (List) var6;
        }
    }

    public static String readFile2String(String filePath) {
        return readFile2String((File) FileUtils.getFileByPath(filePath), (String) null);
    }

    public static String readFile2String(String filePath, String charsetName) {
        return readFile2String(FileUtils.getFileByPath(filePath), charsetName);
    }

    public static String readFile2String(File file) {
        return readFile2String((File) file, (String) null);
    }

    public static String readFile2String(File file, String charsetName) {
        if (!FileUtils.isFileExists(file)) {
            return null;
        } else {
            BufferedReader reader = null;

            String line;
            try {
                StringBuilder sb = new StringBuilder();
                if (isSpace(charsetName)) {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                } else {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
                }

                while ((line = reader.readLine()) != null) {
                    sb.append(line).append(LINE_SEP);
                }

                String var5 = sb.delete(sb.length() - LINE_SEP.length(), sb.length()).toString();
                return var5;
            } catch (IOException var9) {
                var9.printStackTrace();
                line = null;
            } finally {
                CloseUtils.closeIO(new Closeable[]{reader});
            }

            return line;
        }
    }

    public static byte[] readFile2BytesByStream(String filePath) {
        return readFile2BytesByStream(FileUtils.getFileByPath(filePath));
    }

    public static byte[] readFile2BytesByStream(File file) {
        if (!FileUtils.isFileExists(file)) {
            return null;
        } else {
            FileInputStream fis = null;
            ByteArrayOutputStream os = null;

            Object var4;
            try {
                fis = new FileInputStream(file);
                os = new ByteArrayOutputStream();
                byte[] b = new byte[1024];

                int len;
                while ((len = fis.read(b, 0, 1024)) != -1) {
                    os.write(b, 0, len);
                }

                byte[] var5 = os.toByteArray();
                return var5;
            } catch (IOException var9) {
                var9.printStackTrace();
                var4 = null;
            } finally {
                CloseUtils.closeIO(new Closeable[]{fis, os});
            }

            return (byte[]) var4;
        }
    }

    public static byte[] readFile2BytesByChannel(String filePath) {
        return readFile2BytesByChannel(FileUtils.getFileByPath(filePath));
    }

    public static byte[] readFile2BytesByChannel(File file) {
        if (!FileUtils.isFileExists(file)) {
            return null;
        } else {
            FileChannel fc = null;

            Object var3;
            try {
                fc = (new RandomAccessFile(file, "r")).getChannel();
                ByteBuffer byteBuffer = ByteBuffer.allocate((int) fc.size());

                while (fc.read(byteBuffer) > 0) {
                    ;
                }

                byte[] var9 = byteBuffer.array();
                return var9;
            } catch (IOException var7) {
                var7.printStackTrace();
                var3 = null;
            } finally {
                CloseUtils.closeIO(new Closeable[]{fc});
            }

            return (byte[]) var3;
        }
    }

//    public static byte[] readFile2BytesByMap(String filePath) {
//        return readFile2BytesByMap(FileUtils.getFileByPath(filePath));
//    }

//    public static byte[] readFile2BytesByMap(File file) {
//        if (!FileUtils.isFileExists(file)) {
//            return null;
//        } else {
//            FileChannel fc = null;
//
//            MappedByteBuffer mbb;
//            try {
//                fc = (new RandomAccessFile(file, "r")).getChannel();
//                int size = (int) fc.size();
//                mbb = fc.map(MapMode.READ_ONLY, 0L, (long) size).load();
//                byte[] result = new byte[size];
//                mbb.get(result, 0, size);
//                byte[] var5 = result;
//                return var5;
//            } catch (IOException var9) {
//                var9.printStackTrace();
//                mbb = null;
//            } finally {
//                CloseUtils.closeIO(new Closeable[]{fc});
//            }
//
//            return (byte[]) mbb;
//        }
//    }

    private static boolean isSpace(String s) {
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


}
