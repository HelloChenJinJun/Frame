package com.example.commonlibrary.utils;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.MediaType;

public class FileUtil {

    private final static String PATH_PLACEHOLDER_EXTERNAL = "%EXTERNAL%";
    private final static String PATH_PLACEHOLDER_HOME = "%HOME%";
    private final static String PATH_PREFIX_EXTERNAL = "/mnt/sdcard";
    private final static String PATH_PREFIX_EXTERNAL_SDCARD = "/sdcard";



    public static File newFile(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void deleteFile(String path) {
        if (!TextUtils.isEmpty(path)) {
            deleteFile(new File(path));
        }
    }

    public static void deleteFile(File file) {
        deleteFileOrDirectory(file);
    }

    public static boolean exists(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        return file.exists();
    }

    public static boolean mkdir(String path) {
        File dir = new File(path);
        return dir.mkdirs();
    }

    public static boolean copyFile(File srcFile, File dstFile) {
        if (!srcFile.exists() || !srcFile.isFile() || dstFile.isDirectory()) {
            return false;
        }
        if (dstFile.exists()) {
            deleteFile(dstFile);
        }
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            final int BUFFER_SIZE = 2048;
            byte[] buffer = new byte[BUFFER_SIZE];
            input = new BufferedInputStream(new FileInputStream(srcFile));
            output = new BufferedOutputStream(new FileOutputStream(dstFile));
            while (true) {
                int count = input.read(buffer);
                if (count == -1) {
                    break;
                }
                output.write(buffer, 0, count);
            }
            output.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean copyFile(String srcPath, String dstPath) {
        return copyFile(new File(srcPath), new File(dstPath));
    }

    public static String readFileFirstLine(String filePath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            reader.close();
            return line;
        } catch (Exception e) {
            return "";
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void deleteDirectory(String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        deleteDirectory(new File(path));
    }

    public static void deleteDirectory(File file) {
        deleteFileOrDirectory(file);
    }

    private static void deleteFileOrDirectory(File file) {
        if (file == null || !file.exists()) {
            return;
        }

        File fileRenamed = new File(file.getPath() + System.currentTimeMillis());
        File fileToDelete = file.renameTo(fileRenamed) ? fileRenamed : file;

        if (fileToDelete.isDirectory()) {
            removeDirectory(fileToDelete);
        } else {
            //noinspection ResultOfMethodCallIgnored
            fileToDelete.delete();
        }
    }

    private static void removeDirectory(File file) {
        if (file == null || !file.exists()) {
            return;
        }

        List<File> dirs = new ArrayList<>();
        dirs.add(file);
        for (int i = 0; i < dirs.size(); ++i) {
            File[] fileList = dirs.get(i).listFiles();
            if (fileList == null || fileList.length == 0) {
                continue;
            }
            for (File subFile : fileList) {
                if (subFile.isDirectory()) {
                    dirs.add(subFile);
                } else {
                    //noinspection ResultOfMethodCallIgnored
                    subFile.delete();
                }
            }
        }

        for (int i = dirs.size() - 1; i >= 0; --i) {
            //noinspection ResultOfMethodCallIgnored
            dirs.get(i).delete();
        }
    }

    public static long getFileSize(String path) {
        if (TextUtils.isEmpty(path)) {
            return 0;
        }
        File file = new File(path);
        if (!file.exists()) {
            return 0;
        }
        long size = file.length();
        if (file.isDirectory()) {
            File[] childList = file.listFiles();
            if (childList != null) {
                for (File childFile : childList) {
                    try {
                        size += getFileSize(childFile.getAbsolutePath());
                    } catch (StackOverflowError e) {
                        // too many recursion may cause stack over flow
                        e.printStackTrace();
                        return 0;
                    } catch (OutOfMemoryError e2) {
                        // too many call filenamesToFiles method
                        e2.printStackTrace();
                        return 0;
                    }
                }
            }
        }
        return size;
    }


//  /**
//   * This method is used for formatting path using '%HOME%', '%EXTERNAL'.
//   *
//   * @param context
//   * @param path
//   * @return
//   */
//  public static String formatPath(Context context, String path) {
//    if (path.startsWith(PATH_PLACEHOLDER_HOME)) {
//      return formatHomePath(context, path);
//    } else {
//      return formatExternalPath(path);
//    }
//  }

//  public static boolean isExternalPath(String path) {
//    if (path.startsWith(PATH_PLACEHOLDER_HOME)) {
//      return false;
//    } else {
//      return true;
//    }
//  }

//  /**
//   * This method is used for transform '%HOME%' to available paths whether sd
//   * card is mounted or not. This method should be only used for small files
//   * from import/export, backup/restore.
//   *
//   * @param context
//   * @param path
//   * @return
//   */
//  public static String formatHomePath(Context context, String path) {
//    if (path.startsWith(PATH_PLACEHOLDER_HOME)) {
//      if (Environment.getExternalStorageState().equals(
//          Environment.MEDIA_MOUNTED)) {
//        return path.replace(PATH_PLACEHOLDER_HOME, Environment
//            .getExternalStorageDirectory().getAbsolutePath()
//            + "/"
//            + GlobalConfig.getAppRootDir() + "/");
//
//      } else {
//        return path.replace(PATH_PLACEHOLDER_HOME, context
//            .getFilesDir().getAbsolutePath());
//      }
//    }
//    return path;
//  }

    public static boolean isExternalFile(Context context, String path) {
        if (!TextUtils.isEmpty(path)) {
            return path.startsWith(Environment.getExternalStorageDirectory()
                    .getAbsolutePath());
        } else {
            return false;
        }
    }

    public static String getFileName(String path) {
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        int lastIndex = path.lastIndexOf("/");
        if (lastIndex > 0 && lastIndex < path.length() - 1) {
            return path.substring(lastIndex + 1);
        } else {
            return path;
        }
    }

    public static String getParentFilePath(String filePath) {
        if (filePath.endsWith("/")) {
            filePath = filePath.substring(0, filePath.length() - 1);
        }
        int lastSplitIndex = filePath.lastIndexOf("/");
        if (lastSplitIndex >= 0) {
            return filePath.substring(0, lastSplitIndex);
        }
        return null;
    }

    public static String getFileNameWithoutExtension(String path) {
        String filename = getFileName(path);
        if (filename != null && filename.length() > 0) {
            int dotPos = filename.lastIndexOf('.');
            if (0 < dotPos) {
                return filename.substring(0, dotPos);
            }
        }
        return filename;
    }

    public static String getFileExtension(String path) {
        if (TextUtils.isEmpty(path)) {
            return "";
        }

        int index = path.lastIndexOf(".");
        if (index >= 0 && index < path.length() - 1) {
            return path.substring(index + 1).toUpperCase();
        }
        return "";
    }

    public static boolean renameFile(String originPath, String destPath) {
        File origin = new File(originPath);
        File dest = new File(destPath);
        if (!origin.exists()) {
            return false;
        }
        return origin.renameTo(dest);
    }

    /**
     * <b>return the available size of filesystem<b/>
     *
     * @return the number of bytes available on the filesystem rooted at the
     * given File
     */
    public static long getAvailableBytes(String root) {
        long availableBytes = 0L;
        try {
            if (!TextUtils.isEmpty(root) && new File(root).exists()) {
                StatFs stat = new StatFs(root);
                if (Build.VERSION.SDK_INT >= 18) {
                    availableBytes = stat.getBlockSizeLong() * stat.getAvailableBlocksLong();
                } else {
                    // MUST convert int to long so it won't overflow when availableBytes > 4GB
                    availableBytes = (long) stat.getBlockSize() * (long) stat.getAvailableBlocks();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableBytes;
    }

    /**
     * the size of all bytes of current filesystem where root path inside.<br>
     * <b>return the size of filesystem<b/>
     *
     * @param root
     * @return all bytes.
     */
    public static long getAllBytes(String root) {
        long totalBytes = 0L;
        try {
            if (!TextUtils.isEmpty(root) && new File(root).exists()) {
                StatFs stat = new StatFs(root);
                if (Build.VERSION.SDK_INT >= 18) {
                    totalBytes = stat.getBlockSizeLong() * stat.getBlockCountLong();
                } else {
                    // MUST convert int to long so it won't overflow when totalBytes > 4GB
                    totalBytes = (long) stat.getBlockSize() * (long) stat.getBlockCount();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalBytes;
    }

    /**
     * Check If we can perform both creation and deletion on
     * both file and directory under the given directory.
     * <p>
     * As of Android 4.4 KitKat, when operating on the "secondary storage" of a device,
     * such as SD cards, we may manage to create directories but fail to create files.
     *
     * @param directory
     * @return able to write under the given dir.
     */
    public static boolean canWrite(File directory) {
        if (directory == null || !directory.exists()) {
            return false;
        }

        String randomFileName = "." + System.currentTimeMillis() + "_" + new Random().nextLong();
        File testFile = new File(directory, randomFileName);
        boolean canCreateFile;
        try {
            createEmptyFile(testFile);
            canCreateFile = true;
        } catch (IOException ignore) {
            canCreateFile = false;
        }
        boolean canDeleteFile = false;
        if (canCreateFile) {
            canDeleteFile = testFile.delete();
        }

        String testDirCreation = "." + System.currentTimeMillis();
        File testDir = new File(directory, testDirCreation);
        boolean canCreateDir = testDir.mkdir();
        boolean canDeleteDir = false;
        if (canCreateDir) {
            canDeleteDir = testDir.delete();
        }
        return canCreateFile && canDeleteFile && canCreateDir && canDeleteDir;
    }

    public static void createEmptyFile(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("file is null");
        }
        if (file.exists()) {
            throw new IllegalArgumentException("file already exists");
        }
        if (file.isDirectory()) {
            throw new IllegalArgumentException("file is directory");
        }

        new FileOutputStream(file).close();
    }


    /**
     * 根据文件名获取MIME类型
     */
    public static MediaType guessMimeType(String fileName) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        fileName = fileName.replace("#", "");   //解决文件名中含有#号异常的问题
        String contentType = fileNameMap.getContentTypeFor(fileName);
        if (contentType == null) {
            return MediaType.parse("application/octet-stream");
        }
        return MediaType.parse(contentType);
    }

    public static String clipFileName(String path) {
        int index = path.lastIndexOf("/");
        if (index != -1) {
            String expendName = path.substring(index + 1);
            if (expendName.contains("?")) {
                return expendName.substring(0, expendName.indexOf("?"));
            } else {
                return expendName;
            }
        }
        return null;
    }

}
