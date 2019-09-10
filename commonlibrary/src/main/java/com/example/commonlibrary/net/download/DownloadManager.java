package com.example.commonlibrary.net.download;

import android.os.Environment;

import com.example.commonlibrary.utils.FileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称:    Frame
 * 创建人:      陈锦军
 * 创建时间:    2019-08-01     14:33
 */
public class DownloadManager {

    private static DownloadManager instance;
    private Map<String,DownloadTask> map;

    public static DownloadManager getInstance() {
        if (instance == null) {
            instance=new DownloadManager();
        }
        return instance;
    }


    public DownloadBean getDownloadBean(String url){
       DownloadTask downloadTask=map.get(url);
        if (downloadTask!=null) {
            return downloadTask.getDownloadBean();
        }
        return null;
    }


    private DownloadManager(){
        map=new HashMap<>();
    }


    public void download(String url, DownloadTask.DownloadProgressListener downloadProgressListener){
        if (map.get(url) == null) {
            String dir= Environment.getExternalStorageDirectory().getAbsolutePath() + "/music_download/";
            String filePath=dir+DownloadUtil.clipFileName(url);
            FileUtil.newFile(filePath);
            DownloadBean downloadBean=new DownloadBean(dir+DownloadUtil.clipFileName(url),url, DownloadTask.Status.NORMAL);
            DownloadTask downloadTask=new DownloadTask(downloadBean,downloadProgressListener);
            map.put(url,downloadTask);
        }
        map.get(url).start();
    }


    public void pause(String url){
        if (map.get(url) != null) {
            map.get(url).pause();
        }
    }
}
