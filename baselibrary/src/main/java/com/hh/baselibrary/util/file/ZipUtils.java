package com.hh.baselibrary.util.file;


import com.hh.baselibrary.util.check.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by hHui on 2019/5/8 0008.
 */

public class ZipUtils {

    private static final int KB = 1024;

    private ZipUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * @param resFiles    待压缩的文件列表
     * @param zipFilePath 压缩文件 xxxxx.zip
     * @param callback    回调
     * @throws IOException
     */
    public static void zipFiles(final Collection<File> resFiles, final String zipFilePath, final ZipFileCallback callback) throws IOException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (zipFiles(resFiles, zipFilePath, (String) null)) {
                        callback.onSuccess();
                    } else {
                        callback.onFail();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onFail();
                }
            }
        }).start();


    }

    public interface ZipFileCallback {
        void onSuccess();

        void onFail();
    }


    public static boolean zipFiles(Collection<File> resFiles, String zipFilePath, String comment) throws IOException {
        return zipFiles(resFiles, FileUtils.getFileByPath(zipFilePath), comment);
    }

    public static boolean zipFiles(Collection<File> resFiles, File zipFile) throws IOException {
        return zipFiles(resFiles, zipFile, null);
    }

    public static boolean zipFiles(Collection<File> resFiles, File zipFile, String comment) throws IOException {
        if (resFiles != null && zipFile != null) {
            ZipOutputStream zos = null;
            boolean var6;
            try {
                zos = new ZipOutputStream(new FileOutputStream(zipFile));
                Iterator var4 = resFiles.iterator();

                File resFile;
                do {
                    if (!var4.hasNext()) {
                        boolean var10 = true;
                        return var10;
                    }

                    resFile = (File) var4.next();
                } while (zipFile(resFile, "", zos, comment));

                var6 = false;
            } finally {
                if (zos != null) {
                    zos.finish();
                    CloseUtils.closeIO(zos);
                }

            }

            return var6;
        } else {
            return false;
        }
    }

    public static boolean zipFile(String resFilePath, String zipFilePath) throws IOException {
        if (zipFile(resFilePath, zipFilePath, null)) return true;
        else return false;
    }

    public static boolean zipFile(String resFilePath, String zipFilePath, String comment) throws IOException {
        return zipFile(FileUtils.getFileByPath(resFilePath), FileUtils.getFileByPath(zipFilePath), comment);
    }

    public static boolean zipFile(File resFile, File zipFile) throws IOException {
        return zipFile(resFile, zipFile, null);
    }

    public static boolean zipFile(File resFile, File zipFile, String comment) throws IOException {
        if (resFile != null && zipFile != null) {
            ZipOutputStream zos = null;

            boolean var4;
            try {
                zos = new ZipOutputStream(new FileOutputStream(zipFile));
                var4 = zipFile(resFile, "", zos, comment);
            } finally {
                if (zos != null) {
                    CloseUtils.closeIO(zos);
                }

            }

            return var4;
        } else {
            return false;
        }
    }

    private static boolean zipFile(File resFile, String rootPath, ZipOutputStream zos, String comment) throws IOException {
        rootPath = rootPath + (isSpace(rootPath) ? "" : File.separator) + resFile.getName();
        ZipEntry entry;
        int len;
        if (resFile.isDirectory()) {
            File[] fileList = resFile.listFiles();
            if (fileList != null && fileList.length > 0) {
                File[] var13 = fileList;
                int var6 = fileList.length;

                for (len = 0; len < var6; ++len) {
                    File file = var13[len];
                    if (!zipFile(file, rootPath, zos, comment)) {
                        return false;
                    }
                }
            } else {
                entry = new ZipEntry(rootPath + '/');
                if (!StringUtils.isEmpty(comment)) {
                    entry.setComment(comment);
                }

                zos.putNextEntry(entry);
                zos.closeEntry();
            }
        } else {
            BufferedInputStream is = null;

            try {
                is = new BufferedInputStream(new FileInputStream(resFile));
                entry = new ZipEntry(rootPath);
                if (!StringUtils.isEmpty(comment)) {
                    entry.setComment(comment);
                }

                zos.putNextEntry(entry);
                byte[] buffer = new byte[1024];

                while ((len = is.read(buffer, 0, 1024)) != -1) {
                    zos.write(buffer, 0, len);
                }

                zos.closeEntry();
            } finally {
                CloseUtils.closeIO(new Closeable[]{is});
            }
        }

        return true;
    }

    public static boolean unzipFiles(Collection<File> zipFiles, String destDirPath) throws IOException {
        return unzipFiles(zipFiles, FileUtils.getFileByPath(destDirPath));
    }

    public static boolean unzipFiles(Collection<File> zipFiles, File destDir) throws IOException {
        if (zipFiles != null && destDir != null) {
            Iterator var2 = zipFiles.iterator();

            File zipFile;
            do {
                if (!var2.hasNext()) {
                    return true;
                }

                zipFile = (File) var2.next();
            } while (unzipFile(zipFile, destDir));

            return false;
        } else {
            return false;
        }
    }

    public static boolean unzipFile(String zipFilePath, String destDirPath) throws IOException {
        return unzipFile(FileUtils.getFileByPath(zipFilePath), FileUtils.getFileByPath(destDirPath));
    }

    public static boolean unzipFile(File zipFile, File destDir) throws IOException {
        return unzipFileByKeyword(zipFile, destDir, null) != null;
    }

    public static List<File> unzipFileByKeyword(String zipFilePath, String destDirPath, String keyword) throws IOException {
        return unzipFileByKeyword(FileUtils.getFileByPath(zipFilePath), FileUtils.getFileByPath(destDirPath), keyword);
    }

    public static List<File> unzipFileByKeyword(File zipFile, File destDir, String keyword) throws IOException {
        if (zipFile != null && destDir != null) {
            ArrayList files = new ArrayList();
            ZipFile zf = new ZipFile(zipFile);
            Enumeration entries = zf.entries();

            while (true) {
                while (true) {
                    ZipEntry entry;
                    String entryName;
                    do {
                        if (!entries.hasMoreElements()) {
                            return files;
                        }

                        entry = (ZipEntry) entries.nextElement();
                        entryName = entry.getName();
                    }
                    while (!StringUtils.isEmpty(keyword) && !FileUtils.getFileName(entryName).toLowerCase().contains(keyword.toLowerCase()));

                    String filePath = destDir + File.separator + entryName;
                    File file = new File(filePath);
                    files.add(file);
                    if (!entry.isDirectory()) {
                        if (!FileUtils.createOrExistsFile(file)) {
                            return null;
                        }

                        InputStream in = null;
                        BufferedOutputStream out = null;

                        try {
                            in = new BufferedInputStream(zf.getInputStream(entry));
                            out = new BufferedOutputStream(new FileOutputStream(file));
                            byte[] buffer = new byte[1024];

                            int len;
                            while ((len = in.read(buffer)) != -1) {
                                out.write(buffer, 0, len);
                            }
                        } finally {
                            CloseUtils.closeIO(in, out);
                        }
                    } else if (!FileUtils.createOrExistsDir(file)) {
                        return null;
                    }
                }
            }
        } else {
            return null;
        }
    }

    public static List<String> getFilesPath(String zipFilePath) throws IOException {
        return getFilesPath(FileUtils.getFileByPath(zipFilePath));
    }

    public static List<String> getFilesPath(File zipFile) throws IOException {
        if (zipFile == null) {
            return null;
        } else {
            ArrayList paths = new ArrayList();
            Enumeration entries = getEntries(zipFile);

            while (entries.hasMoreElements()) {
                paths.add(((ZipEntry) entries.nextElement()).getName());
            }

            return paths;
        }
    }

    public static List<String> getComments(String zipFilePath) throws IOException {
        return getComments(FileUtils.getFileByPath(zipFilePath));
    }

    public static List<String> getComments(File zipFile) throws IOException {
        if (zipFile == null) {
            return null;
        } else {
            ArrayList comments = new ArrayList();
            Enumeration entries = getEntries(zipFile);

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                comments.add(entry.getComment());
            }

            return comments;
        }
    }

    public static Enumeration<?> getEntries(String zipFilePath) throws IOException {
        return getEntries(FileUtils.getFileByPath(zipFilePath));
    }

    public static Enumeration<?> getEntries(File zipFile) throws IOException {
        return zipFile == null ? null : (new ZipFile(zipFile)).entries();
    }

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
