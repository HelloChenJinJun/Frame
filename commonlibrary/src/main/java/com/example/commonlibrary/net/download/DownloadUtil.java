package com.example.commonlibrary.net.download;

import android.os.Handler;
import android.os.Looper;

import com.example.commonlibrary.utils.CommonLogger;

import java.io.Closeable;
import java.net.FileNameMap;
import java.net.URLConnection;

import okhttp3.MediaType;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-07-17     20:43
 */
public class DownloadUtil {
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



    public static void close(Closeable closeable){
        if (closeable == null) return;
        try {
            closeable.close();
        } catch (Exception e) {
            CommonLogger.printStackTrace(e);
        }
    }


    private static Handler mainHandler=new Handler(Looper.getMainLooper());

    public static void runOnUiThread(Runnable runnable){
        mainHandler.post(runnable);
    }
}
